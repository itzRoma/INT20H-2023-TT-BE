package org.sevenorganization.int20h2023ttbe.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long localId; // id from our database

    @Column(unique = true)
    private Long externalId; // "idMeal" from api

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Meal meal = (Meal) o;
        return localId != null && Objects.equals(localId, meal.localId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
