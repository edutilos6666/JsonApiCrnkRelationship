package com.codenotfound.crnk;

import com.codenotfound.crnk.domain.model.ChartistChartDataPieChart;
import com.codenotfound.crnk.domain.repository.ChartistChartDataPieChartRepository;
import com.codenotfound.crnk.domain.repository.ChartistChartDataPieChartRepositoryImpl;
import io.crnk.core.queryspec.FilterOperator;
import io.crnk.core.queryspec.FilterSpec;
import io.crnk.core.queryspec.QuerySpec;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ChartistChartPieBarRepositoryTest extends TestCase {
    private ChartistChartDataPieChartRepository repository;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new ChartistChartDataPieChartRepositoryImpl();
        ChartistChartDataPieChart data1, data2, data3, data4;
        data1 = new ChartistChartDataPieChart("Monday",10.0);
        data2 = new ChartistChartDataPieChart("Tuesday", 20.0);
        data3 = new ChartistChartDataPieChart("Wednesday", 30.0);
        data4 = new ChartistChartDataPieChart("Monday", 40.0);
        //That works as well
//        repository.save(data1);
//        repository.save(data2);
//        repository.save(data3);

        repository.create(data1);
        repository.create(data2);
        repository.create(data3);
        repository.create(data4);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        ((ChartistChartDataPieChartRepositoryImpl)repository).closeFactory();
    }

    public void testCreate() {
        List<ChartistChartDataPieChart> all = repository.findAll(new QuerySpec(ChartistChartDataPieChart.class));
        all.forEach(System.out::println);
        assertThat(all.size(), is(4));
        assertThat(all.get(0).getLabel(), is("Monday"));
        assertThat(all.get(0).getContent(), is(10.0));
    }

    public void testSave() {
        repository.save(new ChartistChartDataPieChart(1L, "Friday", -10.0));
        List<ChartistChartDataPieChart> all = repository.findAll(new QuerySpec(ChartistChartDataPieChart.class));
        all.forEach(System.out::println);
        assertThat(all.size(), is(4));
        assertThat(all.get(0).getLabel(), is("Friday"));
        assertThat(all.get(0).getContent(), is(-10.0));
    }

    public void testDelete() {
        repository.delete(1L);
        List<ChartistChartDataPieChart> all = repository.findAll(new QuerySpec(ChartistChartDataPieChart.class));
        assertThat(all.size(), is(3));
        assertThat(all.get(0).getLabel(), is("Tuesday"));
        assertThat(all.get(0).getContent(), is(20.0));
    }

    public void testFindById() {
        ChartistChartDataPieChart one = repository.findOne(1L, new QuerySpec(ChartistChartDataPieChart.class));
        assertThat(one.getLabel(), is("Monday"));
        assertThat(one.getContent(), is(10.0));
    }

    public void testFindByLabel() {
        QuerySpec querySpec = new QuerySpec(ChartistChartDataPieChart.class);
        querySpec.addFilter(new FilterSpec(Arrays.asList("label"), FilterOperator.EQ, "Monday"));
        List<ChartistChartDataPieChart> some = repository.findAll(querySpec);
        assertThat(some.size(), is(2));
        assertThat(some.get(0).getContent(), is(10.0));
        assertThat(some.get(1).getContent(), is(40.0));
    }
}
