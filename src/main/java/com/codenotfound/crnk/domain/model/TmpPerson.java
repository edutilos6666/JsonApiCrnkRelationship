package com.codenotfound.crnk.domain.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tmp_person")
@JsonApiResource(type="tmp-people")
@Data
public class TmpPerson {
    @Id
    @GeneratedValue
    @JsonApiId
    private long id;
    private String name;

    public TmpPerson(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TmpPerson(String name) {
        this.name = name;
    }

    public TmpPerson() {
    }
}
