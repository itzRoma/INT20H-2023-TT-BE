package org.sevenorganization.int20h2023ttbe.service;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.IngredientDto;
import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.domain.entity.Ingredient;
import org.sevenorganization.int20h2023ttbe.domain.entity.Meal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserService userService;
    private final MealService mealService;
    private final IngredientService ingredientService;

    public List<MealDto> getSavedMeals(String userEmail) {
        return userService.findUserByEmail(userEmail).getSavedMeals().stream()
                .map(meal -> mealService.getMealByExternalId(meal.getExternalId()))
                .toList();
    }

    public MealDto addMealToProfile(Meal meal, String userEmail) {
        final MealDto mealDto = mealService.saveMealIfNotExists(meal);
        userService.saveMeal(meal, userService.findUserByEmail(userEmail));
        return mealDto;
    }

    public void deleteMealFromProfile(Long externalMealId, String userEmail) {
        userService.deleteMeal(
                mealService.findMealByExternalIdFromDB(externalMealId),
                userService.findUserByEmail(userEmail)
        );
    }

    public List<IngredientDto> getSavedIngredients(String userEmail) {
        return userService.findUserByEmail(userEmail).getSavedIngredients().stream()
                .map(ingredient -> ingredientService.getIngredientByExternalId(ingredient.getExternalId()))
                .toList();
    }

    public IngredientDto addIngredientToProfile(Ingredient ingredient, String userEmail) {
        final IngredientDto ingredientDto = ingredientService.saveIngredientIfNotExists(ingredient);
        userService.saveIngredient(ingredient, userService.findUserByEmail(userEmail));
        return ingredientDto;
    }

    public void deleteIngredientFromProfile(Long externalIngredientId, String userEmail) {
        userService.deleteIngredient(
                ingredientService.findIngredientByExternalIdFromDB(externalIngredientId),
                userService.findUserByEmail(userEmail)
        );
    }
}
