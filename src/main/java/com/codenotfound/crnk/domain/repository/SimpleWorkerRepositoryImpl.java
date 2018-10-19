package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.SimpleWorker;
import io.crnk.core.queryspec.FilterSpec;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.DefaultResourceList;
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
public class SimpleWorkerRepositoryImpl extends ResourceRepositoryBase<SimpleWorker, Long> implements SimpleWorkerRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }

    private Map<Long,SimpleWorker> container = new ConcurrentHashMap<>();

    protected SimpleWorkerRepositoryImpl(Class<SimpleWorker> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public SimpleWorkerRepositoryImpl() {
        this(SimpleWorker.class);
    }

    @Override
    public <S extends SimpleWorker> S save(S resource) {
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
    public <S extends SimpleWorker> S create(S resource) {
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
            SimpleWorker simpleWorker = session.find(SimpleWorker.class, id);
            session.delete(simpleWorker);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public ResourceList<SimpleWorker> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<SimpleWorker> simpleWorkers = null;
        Optional<String> fname = getFilterParam(querySpec, "fname");
        Optional<String> lname = getFilterParam(querySpec, "lname");
        Optional<String> country = getFilterParam(querySpec, "country");
        Optional<String> city = getFilterParam(querySpec, "city");
        Optional<String> plz = getFilterParam(querySpec, "plz");
        Optional<String> email = getFilterParam(querySpec, "email");
        Optional<String> company = getFilterParam(querySpec, "company");
        Optional<Integer> age = getFilterParam(querySpec, "age");
        Optional<Double> wage = getFilterParam(querySpec, "wage");
        Optional<Boolean> active = getFilterParam(querySpec, "active");
        Optional<Integer> minAge =  getFilterParam(querySpec, "minAge");
        Optional<Integer> maxAge = getFilterParam(querySpec, "maxAge");
        Optional<Double> minWage = getFilterParam(querySpec, "minWage");
        Optional<Double> maxWage = getFilterParam(querySpec, "maxWage");
        if(fname.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where fname = :fname", SimpleWorker.class)
                    .setParameter("fname", fname.get())
                    .getResultList();
        } else if(lname.isPresent())  {
            simpleWorkers = session.createQuery("from SimpleWorker where lname = :lname", SimpleWorker.class)
                    .setParameter("lname", lname.get())
                    .getResultList();
        } else if(country.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where country = :country", SimpleWorker.class)
                    .setParameter("country", country.get())
                    .getResultList();
        } else if(city.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where city = :city", SimpleWorker.class)
                    .setParameter("city", city.get())
                    .getResultList();
        } else if(plz.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where plz = :plz", SimpleWorker.class)
                    .setParameter("plz", plz.get())
                    .getResultList();
        } else if(email.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where email = :email", SimpleWorker.class)
                    .setParameter("email", email.get())
                    .getResultList();
        } else if(company.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where company = :company", SimpleWorker.class)
                    .setParameter("company", company.get())
                    .getResultList();
        } else if(age.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where age = :age", SimpleWorker.class)
                    .setParameter("age", age.get())
                    .getResultList();
        } else if(wage.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where wage =: wage", SimpleWorker.class)
                    .setParameter("wage", wage.get())
                    .getResultList();
        } else if(active.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where active =: active", SimpleWorker.class)
                    .setParameter("active", active.get())
                    .getResultList();
        } else if(minAge.isPresent() && maxAge.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where age >= :minAge and age <= :maxAge", SimpleWorker.class)
                    .setParameter("minAge", minAge.get())
                    .setParameter("maxAge", maxAge.get())
                    .getResultList();
        } else if(minWage.isPresent() && maxWage.isPresent()) {
            simpleWorkers = session.createQuery("from SimpleWorker where wage >= :minWage and wage <= :maxWage", SimpleWorker.class)
                    .setParameter("minWage", minWage.get())
                    .setParameter("maxWage", maxWage.get())
                    .getResultList();
        } else {
            simpleWorkers = session.createQuery("from SimpleWorker", SimpleWorker.class).getResultList();
        }
        session.close();
//        return querySpec.apply(simpleWorkers);
        return new DefaultResourceList<>(simpleWorkers, null, null);
    }

    @Override
    public SimpleWorker findOne(Long id, QuerySpec querySpec) {
        Session session = factory.openSession();
        SimpleWorker ret = session.find(SimpleWorker.class, id);
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
