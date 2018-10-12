package com.codenotfound.crnk.domain.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonApiResource(type="hibernate_universities")
@Entity
@Table(name="hibernate_university")
public class HibernateUniversity {
    @JsonApiId
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String country;
    private String city;
    private String plz;
    private String rector;
    @JsonApiRelation(opposite = "hibernateUniversity")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hibernateUniversity", cascade = CascadeType.ALL)
    private List<HibernateStudent> hibernateStudents = new ArrayList<>();

    public HibernateUniversity(Long id, String name, String country, String city, String plz, String rector) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.plz = plz;
        this.rector = rector;
    }

    public HibernateUniversity(String name, String country, String city, String plz, String rector) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.plz = plz;
        this.rector = rector;
    }


    public HibernateUniversity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getRector() {
        return rector;
    }

    public void setRector(String rector) {
        this.rector = rector;
    }

    public List<HibernateStudent> getHibernateStudents() {
        return hibernateStudents;
    }

    public void setHibernateStudents(List<HibernateStudent> hibernateStudents) {
        this.hibernateStudents = hibernateStudents;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", plz='" + plz + '\'' +
                ", rector='" + rector + '\'' +
                ", hibernateStudents=" + hibernateStudents.stream().map(hs -> String.join(" ", hs.getFname(), hs.getLname())) +
                '}';
    }
}
