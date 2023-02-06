package org.sevenorganization.int20h2023ttbe.repository;

import org.sevenorganization.int20h2023ttbe.domain.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    boolean existsByExternalId(Long externalId);

    Optional<Ingredient> findByExternalId(Long externalId);
}
