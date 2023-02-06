package org.sevenorganization.int20h2023ttbe.repository;

import org.sevenorganization.int20h2023ttbe.domain.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    boolean existsByExternalId(Long externalId);

    Optional<Meal> findByExternalId(Long externalId);
}
