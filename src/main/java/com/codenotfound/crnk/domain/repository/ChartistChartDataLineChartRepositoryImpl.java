package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.ChartistChartDataLineChart;
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
public class ChartistChartDataLineChartRepositoryImpl extends ResourceRepositoryBase<ChartistChartDataLineChart, Long> implements ChartistChartDataLineChartRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }
    

    protected ChartistChartDataLineChartRepositoryImpl(Class<ChartistChartDataLineChart> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public ChartistChartDataLineChartRepositoryImpl() {
        this(ChartistChartDataLineChart.class);
    }

    @Override
    public <S extends ChartistChartDataLineChart> S create(S resource) {
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
    public <S extends ChartistChartDataLineChart> S save(S resource) {
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
            ChartistChartDataLineChart chartistChartDataBar = session.find(ChartistChartDataLineChart.class, id);
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
    public ResourceList<ChartistChartDataLineChart> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<ChartistChartDataLineChart> chartistChartDataBars = null;
        Optional<String> label = getFilterParam(querySpec, "label");
        if(label.isPresent()) {
            chartistChartDataBars = session.createQuery("from ChartistChartDataLineChart where label = :label", ChartistChartDataLineChart.class)
                    .setParameter("label", label.get())
                    .getResultList();
        } else {
            chartistChartDataBars = session.createQuery("from ChartistChartDataLineChart", ChartistChartDataLineChart.class).getResultList();
        }
        session.close();
        return querySpec.apply(chartistChartDataBars);
    }

    @Override
    public ChartistChartDataLineChart findOne(Long id, QuerySpec querySpec) {
        Session session = factory.openSession();
        ChartistChartDataLineChart result = session.find(ChartistChartDataLineChart.class, id);
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
