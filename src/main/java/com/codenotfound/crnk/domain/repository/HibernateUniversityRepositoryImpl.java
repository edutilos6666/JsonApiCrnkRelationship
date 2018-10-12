package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.HibernateUniversity;
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
public class HibernateUniversityRepositoryImpl extends ResourceRepositoryBase<HibernateUniversity, Long> implements HibernateUniversityRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }

    private Map<Long,HibernateUniversity> container = new ConcurrentHashMap<>();

    protected HibernateUniversityRepositoryImpl(Class<HibernateUniversity> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public HibernateUniversityRepositoryImpl() {
        this(HibernateUniversity.class);
    }

    @Override
    public <S extends HibernateUniversity> S save(S resource) {
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
            HibernateUniversity university = session.find(HibernateUniversity.class, id);
            Hibernate.initialize(university.getHibernateStudents());
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
    public ResourceList<HibernateUniversity> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<HibernateUniversity> students = session.createQuery("from HibernateUniversity", HibernateUniversity.class).getResultList();
        session.close();
        return querySpec.apply(students);
    }

    @Override
    public HibernateUniversity findOne(Long id, QuerySpec querySpec) {
        Session session = factory.openSession();
        HibernateUniversity ret = session.find(HibernateUniversity.class, id);
        session.close();
        return ret;
    }
}
