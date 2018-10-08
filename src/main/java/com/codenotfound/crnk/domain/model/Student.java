package com.codenotfound.crnk.domain.model;


import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;

@JsonApiResource(type="students")
public class Student {
    @JsonApiId
    private Long id;
    private String fname;
    private String lname;
    private int age;
    private double wage;
    private boolean active;
    @JsonApiRelation(opposite = "students")
    private University university;

    public Student(Long id, String fname, String lname, int age, double wage, boolean active) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public Student() {
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
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
                ", university=" + university +
                '}';
    }
}
