package org.sevenorganization.int20h2023ttbe.service.helper.meal.mapper;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.IngredientDto;
import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.service.IngredientService;
import org.sevenorganization.int20h2023ttbe.service.helper.meal.MealMapper;

import java.util.Map;
import java.util.Optional;

import static org.sevenorganization.int20h2023ttbe.service.helper.meal.MealHelper.STR_INGREDIENT;
import static org.sevenorganization.int20h2023ttbe.service.helper.meal.MealHelper.STR_MEASURE;

@RequiredArgsConstructor
public class IngredientMeasureMealMapper implements MealMapper {

    private final IngredientService ingredientService;

    @Override
    public void mapAttrKey(String key, String value, MealDto mealDto) {
        if (key.startsWith(STR_INGREDIENT)) {
            Optional<IngredientDto> ingredientDto = ingredientService.getIngredientByName(value);
            ingredientDto.ifPresent(dto -> mealDto.getIngredients().put(dto, key));
        } else if (key.startsWith(STR_MEASURE)) {
            mealDto.getIngredients().entrySet().stream()
                    .filter(entry -> isIngredientMeasure(key, entry))
                    .findAny()
                    .ifPresent(entry -> mealDto.getIngredients().put(entry.getKey(), value));
        }
    }

    private boolean isIngredientMeasure(String key, Map.Entry<IngredientDto, String> entry) {
        if (entry == null || entry.getValue() == null) return false;
        return entry.getValue().endsWith(key.substring(key.length() - 1));
    }

}
