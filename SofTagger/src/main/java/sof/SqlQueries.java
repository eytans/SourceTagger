package sof;

import org.stackexchange.dumps.importer.domain.Post;
import org.stackexchange.dumps.importer.services.SofManager;
import org.stackexchange.dumps.importer.services.SofManagerImpl;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.nio.Buffer;
import java.util.List;
import java.util.stream.Stream;

public class SqlQueries {

    public final EntityManager em;

    public SqlQueries() {
        this(SofManagerImpl.create());
    }
    public SqlQueries(SofManager sofManager) {
        this(sofManager.getEm());
    }
    public SqlQueries(EntityManager em) {
        this.em = em;
    }

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

    public List joinPostWithAnswer() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<Post> question = q.from(Post.class);
        Root<Post> answer = q.from(Post.class);
        CriteriaQuery join = q.multiselect(question, answer);
//                .where(cb.equal(question.get("acceptedAnswerId"), answer.get("id")));
        List ret =  em.createQuery(join).getResultList();
        return ret;
    }
}
