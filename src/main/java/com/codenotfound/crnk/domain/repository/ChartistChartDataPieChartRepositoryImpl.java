package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.ChartistChartDataPieChart;
import io.crnk.core.queryspec.FilterSpec;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ChartistChartDataPieChartRepositoryImpl extends ResourceRepositoryBase<ChartistChartDataPieChart, Long> implements ChartistChartDataPieChartRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }
    

    protected ChartistChartDataPieChartRepositoryImpl(Class<ChartistChartDataPieChart> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public ChartistChartDataPieChartRepositoryImpl() {
        this(ChartistChartDataPieChart.class);
    }

    @Override
    public <S extends ChartistChartDataPieChart> S create(S resource) {
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
    public <S extends ChartistChartDataPieChart> S save(S resource) {
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
            ChartistChartDataPieChart chartistChartDataBar = session.find(ChartistChartDataPieChart.class, id);
            session.delete(chartistChartDataBar);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public ResourceList<ChartistChartDataPieChart> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<ChartistChartDataPieChart> chartistChartDataBars = null;
        Optional<String> label = getFilterParam(querySpec, "label");
        if(label.isPresent()) {
            chartistChartDataBars = session.createQuery("from ChartistChartDataPieChart where label = :label", ChartistChartDataPieChart.class)
                    .setParameter("label", label.get())
                    .getResultList();
        } else {
            chartistChartDataBars = session.createQuery("from ChartistChartDataPieChart", ChartistChartDataPieChart.class).getResultList();
        }
        session.close();
        return querySpec.apply(chartistChartDataBars);
    }

    @Override
    public ChartistChartDataPieChart findOne(Long id, QuerySpec querySpec) {
        Session session = factory.openSession();
        ChartistChartDataPieChart result = session.find(ChartistChartDataPieChart.class, id);
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
