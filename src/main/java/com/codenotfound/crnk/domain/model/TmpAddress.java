package com.codenotfound.crnk.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tmp_address")
@JsonApiResource(type="tmp-addresses")
@Getter
@Setter
public class TmpAddress {
    @Id
    @GeneratedValue
    @JsonApiId
    private long id;
    private String city;

    @OneToOne
    @JsonIgnore
    @JsonApiRelation(opposite = "address")
    TmpWorker worker;

    public TmpAddress(String city) {
        this.city = city;
    }

    public TmpAddress() {
    }

    @Override
    public String toString() {
        return "TmpAddress{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}
