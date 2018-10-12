package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.HibernateStudent;
import com.codenotfound.crnk.domain.model.HibernateStudent;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HibernateStudentRepositoryImpl extends ResourceRepositoryBase<HibernateStudent, Long> implements HibernateStudentRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }

    private Map<Long,HibernateStudent> container = new ConcurrentHashMap<>();

    protected HibernateStudentRepositoryImpl(Class<HibernateStudent> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public HibernateStudentRepositoryImpl() {
        this(HibernateStudent.class);
    }

    @Override
    public <S extends HibernateStudent> S save(S resource) {
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
            HibernateStudent student = session.find(HibernateStudent.class, id);
            session.delete(student);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public ResourceList<HibernateStudent> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<HibernateStudent> students = session.createQuery("from HibernateStudent", HibernateStudent.class).getResultList();
        session.close();
        return querySpec.apply(students);
    }
}
