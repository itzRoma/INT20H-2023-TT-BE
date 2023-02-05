package org.sevenorganization.int20h2023ttbe.domain.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class MealDto {
    private String idMeal;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private RecipeDto recipe;
    private String strMealThumb;
    private Set<String> strTags;
    private String strYoutube;
    private Map<IngredientDto, String> ingredients = new HashMap<>();
}
