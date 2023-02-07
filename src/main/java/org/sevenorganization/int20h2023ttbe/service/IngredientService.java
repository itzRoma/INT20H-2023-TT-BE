package org.sevenorganization.int20h2023ttbe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sevenorganization.int20h2023ttbe.domain.dto.IngredientDto;
import org.sevenorganization.int20h2023ttbe.domain.entity.Ingredient;
import org.sevenorganization.int20h2023ttbe.exception.entity.EntityNotFoundException;
import org.sevenorganization.int20h2023ttbe.feign.IngredientFeignClient;
import org.sevenorganization.int20h2023ttbe.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientFeignClient ingredientFeignClient;
    private final IngredientRepository ingredientRepository;

    private List<IngredientDto> allIngredients;

    public List<IngredientDto> getAllIngredients() {
        if (allIngredients == null || allIngredients.isEmpty()) {
            allIngredients = ingredientFeignClient.getAllIngredients().values().stream()
                    .flatMap(Collection::stream)
                    .toList();
        }
        return allIngredients;
    }

    public Optional<IngredientDto> getIngredientByName(String name) {
        return getAllIngredients().stream()
                .filter(ingredientDto -> ingredientDto.strIngredient().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<IngredientDto> getIngredientsByType(String type) {
        return getAllIngredients().stream()
                .filter(ingredientDto -> type.equalsIgnoreCase(ingredientDto.strType()))
                .toList();
    }

    public List<String> getAllIngredientsTypes() {
        return getAllIngredients().stream()
                .map(IngredientDto::strType)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }

    public IngredientDto getIngredientByExternalId(Long externalId) {
        return getAllIngredients().stream()
                .filter(ingredientDto -> Long.parseLong(ingredientDto.idIngredient()) == externalId)
                .findFirst()
                .orElseThrow(() -> EntityNotFoundException.withField(
                        Ingredient.class, "externalId", externalId.toString()
                ));
    }

    public IngredientDto saveIngredientIfNotExists(Ingredient ingredient) {
        if (!ingredientRepository.existsByExternalId(ingredient.getExternalId())) {
            ingredientRepository.save(ingredient);
        }
        return getIngredientByExternalId(ingredient.getExternalId());
    }

    public Ingredient findIngredientByExternalIdFromDB(Long externalId) {
        return ingredientRepository.findByExternalId(externalId).orElseThrow(() -> {
            throw EntityNotFoundException.withField(Ingredient.class, "externalId", externalId.toString());
        });
    }

    public boolean existsByExternalIdInDB(Long externalId) {
        return ingredientRepository.existsByExternalId(externalId);
    }
}
