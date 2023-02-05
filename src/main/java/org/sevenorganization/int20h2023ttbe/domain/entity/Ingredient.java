package org.sevenorganization.int20h2023ttbe.domain.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long localId; // id from our database

    @Column(unique = true)
    private Long externalId; // "idIngredient" from api

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ingredient that = (Ingredient) o;
        return localId != null && Objects.equals(localId, that.localId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
