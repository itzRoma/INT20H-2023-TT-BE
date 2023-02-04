package org.sevenorganization.int20h2023ttbe.resource;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meals")
public class MealResource {

    private final MealService mealService;

    @GetMapping("/{mealExternalId}")
    public ResponseEntity<MealDto> getMealByExternalId(@PathVariable("mealExternalId") Long mealExternalId) {
        return ResponseEntity.ok(mealService.getMealByExternalId(mealExternalId));
    }
}
