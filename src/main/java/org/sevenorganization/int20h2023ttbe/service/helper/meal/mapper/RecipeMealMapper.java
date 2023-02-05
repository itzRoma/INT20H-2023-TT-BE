package org.sevenorganization.int20h2023ttbe.service.helper.meal.mapper;

import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.domain.dto.RecipeDto;
import org.sevenorganization.int20h2023ttbe.service.helper.meal.MealMapper;

import java.util.Arrays;

import static java.util.Objects.nonNull;

public class RecipeMealMapper implements MealMapper {

    private static final String REGEX = "\r\n";

    @Override
    public void mapAttrKey(String key, String value, MealDto mealDto) {
        if (nonNull(value)) {
            var steps = Arrays.stream(value.split(REGEX)).toList();
            mealDto.setRecipe(new RecipeDto(steps, steps.size()));
        }
    }
}
