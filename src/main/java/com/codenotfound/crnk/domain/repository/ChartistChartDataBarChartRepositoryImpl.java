package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.ChartistChartDataBarChart;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChartistChartDataBarChartRepositoryImpl extends ResourceRepositoryBase<ChartistChartDataBarChart, Long> implements ChartistChartDataBarChartRepository {

    private SessionFactory factory;
    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }
    

    protected ChartistChartDataBarChartRepositoryImpl(Class<ChartistChartDataBarChart> resourceClass) {
        super(resourceClass);
        initFactory();
    }

    public ChartistChartDataBarChartRepositoryImpl() {
        this(ChartistChartDataBarChart.class);
    }

    @Override
    public <S extends ChartistChartDataBarChart> S create(S resource) {
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
    public <S extends ChartistChartDataBarChart> S save(S resource) {
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
            ChartistChartDataBarChart chartistChartDataBarChart = session.find(ChartistChartDataBarChart.class, id);
            session.delete(chartistChartDataBarChart);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public ResourceList<ChartistChartDataBarChart> findAll(QuerySpec querySpec) {
        Session session = factory.openSession();
        List<ChartistChartDataBarChart> chartistChartDataBarCharts = session.createQuery("from ChartistChartDataBarChart", ChartistChartDataBarChart.class).getResultList();
        session.close();
        return querySpec.apply(chartistChartDataBarCharts);
    }

    @Override
    public ChartistChartDataBarChart findOne(Long id, QuerySpec querySpec) {
        Session session = factory.openSession();
        ChartistChartDataBarChart result = session.find(ChartistChartDataBarChart.class, id);
        session.close();
        return result;
    }

}
