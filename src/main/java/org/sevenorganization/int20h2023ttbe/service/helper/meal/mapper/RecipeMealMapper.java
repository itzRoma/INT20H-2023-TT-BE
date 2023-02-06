package org.sevenorganization.int20h2023ttbe.service.helper.meal.mapper;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.domain.dto.RecipeComplexity;
import org.sevenorganization.int20h2023ttbe.domain.dto.RecipeDto;
import org.sevenorganization.int20h2023ttbe.service.helper.meal.MealMapper;

import java.util.Arrays;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class RecipeMealMapper implements MealMapper {

    private static final String REGEX = "\r\n";
    private final int easyComplexity;
    private final int mediumComplexity;
    @Override
    public void mapAttrKey(String key, String value, MealDto mealDto) {
        if (nonNull(value)) {
            var steps = Arrays.stream(value.split(REGEX)).toList();
            mealDto.setRecipe(new RecipeDto(steps, defineRecipeComplexity(steps.size())));
        }
    }

    private RecipeComplexity defineRecipeComplexity(int size) {
        if (size <= easyComplexity) {
            return RecipeComplexity.EASY;
        } else if (size <= mediumComplexity) {
            return RecipeComplexity.MEDIUM;
        }
        return RecipeComplexity.HARD;
    }
}
