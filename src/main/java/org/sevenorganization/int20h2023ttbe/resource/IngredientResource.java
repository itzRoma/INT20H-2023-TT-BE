package org.sevenorganization.int20h2023ttbe.resource;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.IngredientDto;
import org.sevenorganization.int20h2023ttbe.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientResource {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<IngredientDto> getIngredientByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(ingredientService.getIngredientByName(name).get());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<IngredientDto>> getIngredientsByType(@PathVariable("type") String type) {
        return ResponseEntity.ok(ingredientService.getIngredientsByType(type));
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getAllIngredientsTypes() {
        return ResponseEntity.ok(ingredientService.getAllIngredientsTypes());
    }
}
