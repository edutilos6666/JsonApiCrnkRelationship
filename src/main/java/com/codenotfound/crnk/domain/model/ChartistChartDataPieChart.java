package com.codenotfound.crnk.domain.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="chartdata_piechart")
@JsonApiResource(type="chartdata-piecharts")
@Data
public class ChartistChartDataPieChart {
    @Id
    @GeneratedValue
    @JsonApiId
    private long id;
    private String label;
    private double content;


    public ChartistChartDataPieChart(long id, String label, double content) {
        this.id = id;
        this.label = label;
        this.content = content;
    }

    public ChartistChartDataPieChart(String label, double content) {
        this.label = label;
        this.content = content;
    }

    public ChartistChartDataPieChart() {
    }
}
