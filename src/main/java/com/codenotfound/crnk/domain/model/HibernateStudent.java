package com.codenotfound.crnk.domain.model;


import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;

@JsonApiResource(type="hibernate_students")
@Entity
@Table(name="hibernate_student")
public class HibernateStudent {
    @JsonApiId
    @Id
    @GeneratedValue
    private Long id;
    private String fname;
    private String lname;
    private int age;
    private double wage;
    private boolean active;
    @JsonApiRelation(opposite = "hibernateStudents")
    @ManyToOne
    private HibernateUniversity hibernateUniversity;

    public HibernateStudent(Long id, String fname, String lname, int age, double wage, boolean active) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public HibernateStudent(String fname, String lname, int age, double wage, boolean active) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public HibernateStudent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public HibernateUniversity getHibernateUniversity() {
        return hibernateUniversity;
    }

    public void setHibernateUniversity(HibernateUniversity hibernateUniversity) {
        this.hibernateUniversity = hibernateUniversity;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age=" + age +
                ", wage=" + wage +
                ", active=" + active +
                ", hibernateUniversity=" + hibernateUniversity.getName() +
                '}';
    }
}
