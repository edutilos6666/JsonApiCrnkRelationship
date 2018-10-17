package com.codenotfound.crnk.domain.model;


import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="chartdata_linechart")
@JsonApiResource(type="chartdata-linecharts")
@Data
public class ChartistChartDataLineChart {
    @Id
    @GeneratedValue
    @JsonApiId
    private long id;
    private String label;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Double> content;

    public ChartistChartDataLineChart(String label, List<Double> content) {
        this.label = label;
        this.content = content;
    }

    public ChartistChartDataLineChart() {
    }
}
