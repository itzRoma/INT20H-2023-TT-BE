package org.sevenorganization.int20h2023ttbe.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.IngredientDto;
import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.domain.entity.Ingredient;
import org.sevenorganization.int20h2023ttbe.domain.entity.Meal;
import org.sevenorganization.int20h2023ttbe.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
@PreAuthorize("isAuthenticated()")
public class ProfileResource {

    private final ProfileService profileService;

    @GetMapping("/meals")
    @ApiOperation(
            value = "Get user's saved meals",
            notes = "By calling this endpoint all the user's saved meals will be returned."
    )
    public ResponseEntity<List<MealDto>> getSavedMeals(Principal principal) {
        return ResponseEntity.ok(profileService.getSavedMeals(principal.getName()));
    }

    @PostMapping("/meals")
    @ApiOperation(
            value = "Add meal to profile",
            notes = "By calling this endpoint provided meal will be added to user's profile and MealDto will be returned."
    )
    public ResponseEntity<MealDto> addMealToProfile(
            @ApiParam(value = "Meal to save", name = "meal", type = "Meal", required = true)
            @RequestBody Meal meal,
            Principal principal
    ) {
        return ResponseEntity.ok(profileService.addMealToProfile(meal, principal.getName()));
    }

    @DeleteMapping("/meals/{externalMealId}")
    @ApiOperation(
            value = "Delete meal from profile",
            notes = "By calling this endpoint a meal with the provided id will be deleted from user's profile."
    )
    public ResponseEntity<Void> deleteMealFromProfile(
            @ApiParam(value = "Meal id from TheMealDb API", name = "externalMealId", type = "Long", required = true)
            @PathVariable("externalMealId") Long externalMealId,
            Principal principal
    ) {
        profileService.deleteMealFromProfile(externalMealId, principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ingredients")
    @ApiOperation(
            value = "Get user's saved ingredients",
            notes = "By calling this endpoint all the user's saved ingredients will be returned."
    )
    public ResponseEntity<List<IngredientDto>> getSavedIngredients(Principal principal) {
        return ResponseEntity.ok(profileService.getSavedIngredients(principal.getName()));
    }

    @PostMapping("/ingredients")
    @ApiOperation(
            value = "Add ingredient to profile",
            notes = "By calling this endpoint provided ingredient will be added to user's profile and IngredientDto will be returned."
    )
    public ResponseEntity<IngredientDto> addIngredientToProfile(
            @ApiParam(value = "Ingredient to save", name = "ingredient", type = "Ingredient", required = true)
            @RequestBody Ingredient ingredient,
            Principal principal
    ) {
        return ResponseEntity.ok(profileService.addIngredientToProfile(ingredient, principal.getName()));
    }

    @DeleteMapping("/ingredients/{externalIngredientId}")
    @ApiOperation(
            value = "Delete ingredient from profile",
            notes = "By calling this endpoint an ingredient with the provided id will be deleted from user's profile."
    )
    public ResponseEntity<Void> deleteIngredientFromProfile(
            @ApiParam(value = "Ingredient id from TheMealDb API", name = "externalIngredientId", type = "Long", required = true)
            @PathVariable("externalIngredientId") Long externalIngredientId,
            Principal principal
    ) {
        profileService.deleteIngredientFromProfile(externalIngredientId, principal.getName());
        return ResponseEntity.ok().build();
    }
}
