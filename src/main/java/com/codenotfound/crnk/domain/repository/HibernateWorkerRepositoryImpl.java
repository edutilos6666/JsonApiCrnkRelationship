package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.HibernateWorker;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
//@CrossOrigin("http://localhost:4200")
public class HibernateWorkerRepositoryImpl extends ResourceRepositoryBase<HibernateWorker, Long> implements HibernateWorkerRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }

    private Map<Long,HibernateWorker> container = new ConcurrentHashMap<>();

    protected HibernateWorkerRepositoryImpl(Class<HibernateWorker> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public HibernateWorkerRepositoryImpl() {
        this(HibernateWorker.class);
    }

    @Override
    public <S extends HibernateWorker> S save(S resource) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            session.save(resource);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            return resource;
        }
    }

    @Override
    public void delete(Long id) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            HibernateWorker worker = session.find(HibernateWorker.class, id);
            session.delete(worker);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public ResourceList<HibernateWorker> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<HibernateWorker> workers = session.createQuery("from HibernateWorker", HibernateWorker.class).getResultList();
        session.close();
        return querySpec.apply(workers);
    }

    @Override
    public HibernateWorker findOne(Long id, QuerySpec querySpec) {
        Session session = factory.openSession();
        HibernateWorker ret = session.find(HibernateWorker.class, id);
        session.close();
        return ret;
    }
}
