package sof;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.stackexchange.dumps.importer.services.SofManager;
import org.stackexchange.dumps.importer.services.SofManagerImpl;
import org.stackexchange.querying.PostR;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class SqlQueries {

    /* --- Members --- */
    public final EntityManager em;

    /* --- Constructors --- */
    public SqlQueries() {
        this(SofManagerImpl.create());
    }
    public SqlQueries(SofManager sofManager) {
        this(sofManager.getEm());
    }

    /* --- Public Methods --- */

    public <T> CriteriaQuery<T> typedCriteria(Class<T> clazz) {
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(clazz);
        return query.select(query.from(clazz));
    }

    public <T> List<T> executeCriteria(CriteriaQuery<T> query) {
        return em.createQuery(query).getResultList();
    }

    public <T> List<T> getAll(Class<T> clazz) {
        return executeCriteria(typedCriteria(clazz));
    }

    public List<PostR> getAllQuestions() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PostR> q = cb.createQuery(PostR.class);
        Root<PostR> question = q.from(PostR.class);
        Root<PostR> answer = q.from(PostR.class);
        q = q.where(cb.equal(question.get(PostR.ACCEPTED_ANSWER_ID), answer.get(PostR.ID)));

        return em.createQuery(q).getResultList().stream().map(PostR::getAnswer).collect(toList());
    }
}
