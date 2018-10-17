package com.codenotfound.crnk.client;

import com.codenotfound.crnk.domain.model.ChartistChartDataBarChart;

import java.util.List;

public class BlogClientRunner {
    public static void main(String[] args) {
        blogClient = new BlogClient();
        blogClient.init();
        testChartistChartDataBar();
    }

    private static BlogClient blogClient;
    private static void testChartistChartDataBar() {
//        ChartistChartDataBarChart data1, data2, data3;
//        data1 = new ChartistChartDataBarChart("Monday", Arrays.asList(10.0, 20.0, 30.0));
//        data2 = new ChartistChartDataBarChart("Tuesday", Stream.of(20.0, 30.0, 40.0).collect(Collectors.toList()));
//        data3 = new ChartistChartDataBarChart("Wednesday", Stream.of(30.0, 40.0, 50.0).collect(Collectors.toList()));
//        blogClient.saveChartistChartDataBar(data1);
//        blogClient.saveChartistChartDataBar(data2);
//        blogClient.saveChartistChartDataBar(data3);
        List<ChartistChartDataBarChart> all = blogClient.findAllChartistChartDataBar();
        System.out.println("<<all ChartistChartDataBars>>");
        all.forEach(System.out::println);
    }
}
