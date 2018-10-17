package com.codenotfound.crnk.domain.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tmp_worker")
@JsonApiResource(type="tmp-workers")
@Getter
@Setter
public class TmpWorker {
    @Id
    @GeneratedValue
    @JsonApiId
    private long id;
    private String name;
    @OneToOne
    @JsonApiRelation(opposite = "worker")
    private TmpAddress address;

    public TmpWorker(String name) {
        this.name = name;
    }

    public TmpWorker() {
    }

    @Override
    public String toString() {
        return "TmpWorker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
