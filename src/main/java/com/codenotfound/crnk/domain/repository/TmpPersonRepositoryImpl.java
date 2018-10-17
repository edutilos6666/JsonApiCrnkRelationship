package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.TmpPerson;
import io.crnk.core.queryspec.FilterSpec;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TmpPersonRepositoryImpl extends ResourceRepositoryBase<TmpPerson, Long> implements TmpPersonRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }

    private Map<Long,TmpPerson> container = new ConcurrentHashMap<>();

    protected TmpPersonRepositoryImpl(Class<TmpPerson> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public TmpPersonRepositoryImpl() {
        this(TmpPerson.class);
    }

    @Override
    public <S extends TmpPerson> S save(S resource) {
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
    public <S extends TmpPerson> S create(S resource) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
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
            TmpPerson university = session.find(TmpPerson.class, id);
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
    public ResourceList<TmpPerson> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<TmpPerson> people = null;
        Optional<String> name = getFilterParam(querySpec, "name");
        if(name.isPresent()) {
            people = session.createQuery("from TmpPerson where name = :name", TmpPerson.class)
                    .setParameter("name", name.get())
                    .getResultList();
        } else {
            people = session.createQuery("from TmpPerson", TmpPerson.class).getResultList();
        }
        session.close();
        return querySpec.apply(people);
    }

    @Override
    public TmpPerson findOne(Long id, QuerySpec querySpec) {
        Session session = factory.openSession();
        TmpPerson ret = session.find(TmpPerson.class, id);
        session.close();
        return ret;
    }


    @SuppressWarnings("unchecked")
    public <O> Optional<O> getFilterParam(QuerySpec querySpec, String field) {
        try {
            return (Optional<O>) querySpec.getFilters().stream()
                    .filter(d -> !d.getAttributePath().isEmpty()
                            && d.getAttributePath().get(0).equals(field))
                    .map(FilterSpec::getValue).findFirst();
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
