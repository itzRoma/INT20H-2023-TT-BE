package org.sevenorganization.int20h2023ttbe.service;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.IngredientDto;
import org.sevenorganization.int20h2023ttbe.exception.entity.EntityNotFoundException;
import org.sevenorganization.int20h2023ttbe.feign.IngredientFeignClient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientFeignClient ingredientFeignClient;

    private List<IngredientDto> allIngredients;

    public List<IngredientDto> getAllIngredients() {
        if (allIngredients == null || allIngredients.isEmpty()) {
            allIngredients = ingredientFeignClient.getAllIngredients().values().stream()
                    .flatMap(Collection::stream)
                    .toList();
        }
        return allIngredients;
    }

    public IngredientDto getIngredientByName(String name) {
        return getAllIngredients().stream()
                .filter(ingredientDto -> ingredientDto.strIngredient().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> EntityNotFoundException.withField(
                        IngredientDto.class, "name", name
                ));
    }
}
