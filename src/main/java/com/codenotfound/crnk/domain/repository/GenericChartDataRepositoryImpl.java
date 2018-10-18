package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.GenericChartData;
import io.crnk.core.queryspec.FilterSpec;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GenericChartDataRepositoryImpl extends ResourceRepositoryBase<GenericChartData, Long> implements GenericChartDataRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }


    protected GenericChartDataRepositoryImpl(Class<GenericChartData> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public GenericChartDataRepositoryImpl() {
        this(GenericChartData.class);
    }

    @Override
    public <S extends GenericChartData> S create(S resource) {
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
    public <S extends GenericChartData> S save(S resource) {
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
    public void delete(Long id) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            GenericChartData chartData = session.find(GenericChartData.class, id);
            session.delete(chartData);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public ResourceList<GenericChartData> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<GenericChartData> chartDatas = null;
        Optional<String> label = getFilterParam(querySpec, "label");
        if(label.isPresent()) {
            chartDatas = session.createQuery("from GenericChartData where label = :label", GenericChartData.class)
                    .setParameter("label", label.get())
                    .getResultList();
        } else {
            chartDatas = session.createQuery("from GenericChartData", GenericChartData.class).getResultList();
        }
        session.close();
        return querySpec.apply(chartDatas);
    }

    @Override
    public GenericChartData findOne(Long id, QuerySpec querySpec) {
        Session session = factory.openSession();
        GenericChartData result = session.find(GenericChartData.class, id);
        session.close();
        return result;
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
