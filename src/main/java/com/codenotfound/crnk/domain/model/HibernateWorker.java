package com.codenotfound.crnk.domain.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hibernate_worker")
@JsonApiResource(type = "hibernate-workers")
@Data
public class HibernateWorker {
    @Id
    @GeneratedValue
    @JsonApiId
    private long id;
    private String fname;
    private String lname;
    private String country;
    private String city;
    private String plz;
    private int age;
    private double wage;
    private boolean active;

    public HibernateWorker(String fname, String lname, String country, String city, String plz, int age, double wage, boolean active) {
        this.fname = fname;
        this.lname = lname;
        this.country = country;
        this.city = city;
        this.plz = plz;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public HibernateWorker() {
    }
}
