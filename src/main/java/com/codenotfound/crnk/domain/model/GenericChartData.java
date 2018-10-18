package com.codenotfound.crnk.domain.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="generic_chartdata")
@JsonApiResource(type="generic-chartdatas")
@Data
public class GenericChartData {
    @Id
    @GeneratedValue
    @JsonApiId
    private long id;
    private String type;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> labels;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<GenericSerie> series;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<GenericOption> options;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<GenericOption> responsiveOptions;

    public GenericChartData(String type, List<String> labels, List<GenericSerie> series, List<GenericOption> options, List<GenericOption> responsiveOptions) {
        this.type = type;
        this.labels = labels;
        this.series = series;
        this.options = options;
        this.responsiveOptions = responsiveOptions;
    }

    public GenericChartData(String type, List<String> labels, List<GenericSerie> series) {
        this.type = type;
        this.labels = labels;
        this.series = series;
    }

    public GenericChartData(String type) {
        this.type = type;
    }

    public GenericChartData() {
    }
}
