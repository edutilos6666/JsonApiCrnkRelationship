package com.codenotfound.crnk.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="generic_option")
@Data
public class GenericOption {
    @Id
    @GeneratedValue
    private long id;
    private String key;
    private String value;
    @ManyToOne
    private GenericChartData chartData;

    public GenericOption(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public GenericOption() {
    }
}
