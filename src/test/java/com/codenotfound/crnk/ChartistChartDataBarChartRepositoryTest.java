package com.codenotfound.crnk;

import com.codenotfound.crnk.domain.model.ChartistChartDataBarChart;
import com.codenotfound.crnk.domain.repository.ChartistChartDataBarChartRepository;
import com.codenotfound.crnk.domain.repository.ChartistChartDataBarChartRepositoryImpl;
import io.crnk.core.queryspec.QuerySpec;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ChartistChartDataBarChartRepositoryTest extends TestCase {
    private ChartistChartDataBarChartRepository repository;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new ChartistChartDataBarChartRepositoryImpl();
        ChartistChartDataBarChart data1, data2, data3;
        data1 = new ChartistChartDataBarChart("Monday", Arrays.asList(10.0, 20.0, 30.0));
        data2 = new ChartistChartDataBarChart("Tuesday", Stream.of(20.0, 30.0, 40.0).collect(Collectors.toList()));
        data3 = new ChartistChartDataBarChart("Wednesday", Stream.of(30.0, 40.0, 50.0).collect(Collectors.toList()));
        //That works as well
//        repository.save(data1);
//        repository.save(data2);
//        repository.save(data3);

        repository.create(data1);
        repository.create(data2);
        repository.create(data3);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        ((ChartistChartDataBarChartRepositoryImpl)repository).closeFactory();
    }

    public void testCreate() {
        List<ChartistChartDataBarChart> all = repository.findAll(new QuerySpec(ChartistChartDataBarChart.class));
        all.forEach(System.out::println);
        assertThat(all.size(), is(3));
        assertThat(all.get(0).getLabel(), is("Monday"));
        assertThat(all.get(0).getContent().size(), is(3));
    }

    public void testSave() {
        repository.save(new ChartistChartDataBarChart(1L, "Friday", Arrays.asList(-10.0, -20.0, -30.0)));
        List<ChartistChartDataBarChart> all = repository.findAll(new QuerySpec(ChartistChartDataBarChart.class));
        all.forEach(System.out::println);
        assertThat(all.size(), is(3));
        assertThat(all.get(0).getLabel(), is("Friday"));
        assertThat(all.get(0).getContent().size(), is(3));
    }

    public void testDelete() {
        repository.delete(1L);
        List<ChartistChartDataBarChart> all = repository.findAll(new QuerySpec(ChartistChartDataBarChart.class));
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getLabel(), is("Tuesday"));
        assertThat(all.get(0).getContent().size(), is(3));
    }

    public void testFindById() {
        ChartistChartDataBarChart one = repository.findOne(1L, new QuerySpec(ChartistChartDataBarChart.class));
        assertThat(one.getLabel(), is("Monday"));
        assertThat(one.getContent().size(), is(3));
    }
}
