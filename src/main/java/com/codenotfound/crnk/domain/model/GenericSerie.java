package com.codenotfound.crnk.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="generic_serie")
@Data
public class GenericSerie {
    @Id
    @GeneratedValue
    private long id;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Double> content;
    @ManyToOne
    private GenericChartData chartData;

    public GenericSerie(List<Double> content) {
        this.content = content;
    }

    public GenericSerie() {
    }
}
