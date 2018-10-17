package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.TmpAddress;
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
public class TmpAddressRepositoryImpl extends ResourceRepositoryBase<TmpAddress, Long> implements TmpAddressRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }

    private Map<Long,TmpAddress> container = new ConcurrentHashMap<>();

    protected TmpAddressRepositoryImpl(Class<TmpAddress> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public TmpAddressRepositoryImpl() {
        this(TmpAddress.class);
    }

    @Override
    public <S extends TmpAddress> S save(S resource) {
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
    public <S extends TmpAddress> S create(S resource) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
//            Hibernate.initialize(resource.getWorker());
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
            TmpAddress university = session.find(TmpAddress.class, id);
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
    public ResourceList<TmpAddress> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<TmpAddress> addresses = session.createQuery("from TmpAddress", TmpAddress.class).getResultList();
        session.close();
        return querySpec.apply(addresses);
    }

    @Override
    public TmpAddress findOne(Long id, QuerySpec querySpec) {
        Session session = factory.openSession();
        TmpAddress ret = session.find(TmpAddress.class, id);
        session.close();
        return ret;
    }
}
