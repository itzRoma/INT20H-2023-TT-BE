package org.sevenorganization.int20h2023ttbe.resource;

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
    public ResponseEntity<MealDto> getMealByExternalId(@PathVariable("mealExternalId") Long mealExternalId) {
        return ResponseEntity.ok(mealService.getMealByExternalId(mealExternalId));
    }

    @GetMapping("/letter/{firstLetter}")
    public ResponseEntity<List<MealDto>> getMealsByFirstLetter(@PathVariable("firstLetter") String firstLetter) {
        return ResponseEntity.ok(mealService.getMealsByFirstLetter(firstLetter));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/letter/{firstLetter}/available")
    public ResponseEntity<List<MealDto>> getAvailableMealsByFirstLetter(
            @PathVariable("firstLetter") String firstLetter, Principal principal
    ) {
        List<MealDto> mealByFirstLetter = mealService.getMealsByFirstLetter(firstLetter);
        List<IngredientDto> usersIngredients = profileService.getSavedIngredients(principal.getName());

        final List<MealDto> availableMeals = mealByFirstLetter.stream()
                .filter(mealDto -> new HashSet<>(usersIngredients).containsAll(mealDto.getIngredients().keySet()))
                .toList();

        return ResponseEntity.ok(availableMeals);
    }
}
