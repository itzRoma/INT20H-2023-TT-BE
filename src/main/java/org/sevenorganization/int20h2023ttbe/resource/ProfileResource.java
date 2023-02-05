package org.sevenorganization.int20h2023ttbe.resource;

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
    public ResponseEntity<List<MealDto>> getSavedMeals(Principal principal) {
        return ResponseEntity.ok(profileService.getSavedMeals(principal.getName()));
    }

    @PostMapping("/meals")
    public ResponseEntity<MealDto> addMealToProfile(@RequestBody Meal meal, Principal principal) {
        return ResponseEntity.ok(profileService.addMealToProfile(meal, principal.getName()));
    }

    @DeleteMapping("/meals/{externalMealId}")
    public ResponseEntity<Void> deleteMealFromProfile(@PathVariable("externalMealId") Long externalMealId,
                                                      Principal principal) {
        profileService.deleteMealFromProfile(externalMealId, principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientDto>> getSavedIngredients(Principal principal) {
        return ResponseEntity.ok(profileService.getSavedIngredients(principal.getName()));
    }

    @PostMapping("/ingredients")
    public ResponseEntity<IngredientDto> addIngredientToProfile(@RequestBody Ingredient ingredient, Principal principal) {
        return ResponseEntity.ok(profileService.addIngredientToProfile(ingredient, principal.getName()));
    }

    @DeleteMapping("/ingredients/{externalIngredientId}")
    public ResponseEntity<Void> deleteIngredientFromProfile(@PathVariable("externalIngredientId") Long externalIngredientId,
                                                            Principal principal) {
        profileService.deleteIngredientFromProfile(externalIngredientId, principal.getName());
        return ResponseEntity.ok().build();
    }
}
