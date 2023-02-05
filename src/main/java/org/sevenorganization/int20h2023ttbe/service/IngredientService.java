package org.sevenorganization.int20h2023ttbe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sevenorganization.int20h2023ttbe.domain.dto.IngredientDto;
import org.sevenorganization.int20h2023ttbe.feign.IngredientFeignClient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
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

    public Optional<IngredientDto> getIngredientByName(String name) {
        return getAllIngredients().stream()
                .filter(ingredientDto -> ingredientDto.strIngredient().equalsIgnoreCase(name))
                .findFirst();
    }
}
