package org.stackexchange.dumps.importer.services;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.stackexchange.dumps.importer.contexts.ImporterContext;
import org.stackexchange.dumps.importer.domain.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SofManagerImpl implements SofManager {

    @PersistenceContext
    public EntityManager em;

    public static SofManager create() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImporterContext.class);
        return context.getBean(SofManager.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }

//    @Transactional
//    public List<Tag> getTags() {
//        return em.createQuery("SELECT DISTINCT l FROM  l", Tag.class).getResultList();
//    }
}
