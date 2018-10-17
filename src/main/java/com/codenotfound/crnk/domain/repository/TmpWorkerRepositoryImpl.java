package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.TmpWorker;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TmpWorkerRepositoryImpl extends ResourceRepositoryBase<TmpWorker, Long> implements TmpWorkerRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }

    private Map<Long,TmpWorker> container = new ConcurrentHashMap<>();

    protected TmpWorkerRepositoryImpl(Class<TmpWorker> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public TmpWorkerRepositoryImpl() {
        this(TmpWorker.class);
    }

    @Override
    public <S extends TmpWorker> S save(S resource) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            session.saveOrUpdate(resource);
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
    public <S extends TmpWorker> S create(S resource) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
//            Hibernate.initialize(resource.getAddress());
            session.persist(resource);
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
            TmpWorker university = session.find(TmpWorker.class, id);
            session.delete(university);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public ResourceList<TmpWorker> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<TmpWorker> workers = session.createQuery("from TmpWorker", TmpWorker.class).getResultList();
        session.close();
        return querySpec.apply(workers);
    }

    @Override
    public TmpWorker findOne(Long id, QuerySpec querySpec) {
        Session session = factory.openSession();
        TmpWorker ret = session.find(TmpWorker.class, id);
        session.close();
        return ret;
    }
}
