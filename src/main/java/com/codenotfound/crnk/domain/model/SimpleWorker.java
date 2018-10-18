package com.codenotfound.crnk.domain.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="simple_worker")
@JsonApiResource(type="simple-workers")
@Data
public class SimpleWorker {
    @Id
    @GeneratedValue
    @JsonApiId
    private long id;
    private String fname;
    private String lname;
    private String country;
    private String city;
    private String plz;
    private String email;
    private String company;
    private int age;
    private double wage;
    private boolean active;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> activities;

    public SimpleWorker(String fname, String lname, String country, String city, String plz, String email, String company, int age, double wage, boolean active, List<String> activities) {
        this.fname = fname;
        this.lname = lname;
        this.country = country;
        this.city = city;
        this.plz = plz;
        this.email = email;
        this.company = company;
        this.age = age;
        this.wage = wage;
        this.active = active;
        this.activities = activities;
    }

    public SimpleWorker() {
    }
}
