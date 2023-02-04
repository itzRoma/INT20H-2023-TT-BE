package org.sevenorganization.int20h2023ttbe.domain.dto;

import java.util.Map;
import java.util.Set;

public record MealDto(
        String idMeal,
        String strMeal,
        String strCategory,
        String strArea,
        RecipeDto recipe,
        String strMealThumb,
        Set<String> tags,
        String strYoutube,
        Map<IngredientDto, String> ingredients
) {
}
