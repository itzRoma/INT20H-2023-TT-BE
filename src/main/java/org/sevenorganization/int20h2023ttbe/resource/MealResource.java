package org.sevenorganization.int20h2023ttbe.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.IngredientDto;
import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.service.MealService;
import org.sevenorganization.int20h2023ttbe.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meals")
public class MealResource {

    private final MealService mealService;
    private final ProfileService profileService;

    @GetMapping("/id/{mealExternalId}")
    @ApiOperation(
            value = "Get meal by idMeal from TheMealDB API",
            notes = "By calling this endpoint a meal with the provided id will be returned."
    )
    public ResponseEntity<MealDto> getMealByExternalId(
            @ApiParam(value = "Meal id from TheMealDB API", name = "mealExternalId", type = "Long", required = true)
            @PathVariable("mealExternalId") Long mealExternalId
    ) {
        return ResponseEntity.ok(mealService.getMealByExternalId(mealExternalId));
    }

    @GetMapping("/letter/{firstLetter}")
    @ApiOperation(
            value = "Get meal by first letter",
            notes = "By calling this endpoint meals that starts with the provided letter will be returned."
    )
    public ResponseEntity<List<MealDto>> getMealsByFirstLetter(
            @ApiParam(value = "First letter", name = "firstLetter", type = "String", required = true)
            @PathVariable("firstLetter") String firstLetter
    ) {
        return ResponseEntity.ok(mealService.getMealsByFirstLetter(firstLetter));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/letter/{firstLetter}/available")
    @ApiOperation(
            value = "Get all the available meals for user's saved ingredients",
            notes = "By calling this endpoint all the available meals for user's saved ingredients will be returned."
    )
    public ResponseEntity<List<MealDto>> getAvailableMealsByFirstLetter(
            @ApiParam(value = "First letter", name = "firstLetter", type = "String", required = true)
            @PathVariable("firstLetter") String firstLetter,
            Principal principal
    ) {
        List<MealDto> mealByFirstLetter = mealService.getMealsByFirstLetter(firstLetter);
        List<IngredientDto> usersIngredients = profileService.getSavedIngredients(principal.getName());

        final List<MealDto> availableMeals = mealByFirstLetter.stream()
                .filter(mealDto -> new HashSet<>(usersIngredients).containsAll(mealDto.getIngredients().keySet()))
                .toList();

        return ResponseEntity.ok(availableMeals);
    }
}
