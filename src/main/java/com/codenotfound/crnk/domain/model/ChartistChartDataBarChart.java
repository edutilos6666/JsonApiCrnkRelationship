package com.codenotfound.crnk.domain.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="chartdata_barchart")
@JsonApiResource(type="chartdata-barcharts")
@Data
public class ChartistChartDataBarChart {
    @Id
    @GeneratedValue
    @JsonApiId
    private long id;
    private String label;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Double> content;


    public ChartistChartDataBarChart(long id, String label, List<Double> content) {
        this.id = id;
        this.label = label;
        this.content = content;
    }

    public ChartistChartDataBarChart(String label, List<Double> content) {
        this.label = label;
        this.content = content;
    }

    public ChartistChartDataBarChart() {
    }
}
