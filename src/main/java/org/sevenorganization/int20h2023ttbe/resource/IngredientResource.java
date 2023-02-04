package org.sevenorganization.int20h2023ttbe.resource;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.IngredientDto;
import org.sevenorganization.int20h2023ttbe.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{name}")
    public ResponseEntity<IngredientDto> getIngredientByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(ingredientService.getIngredientByName(name));
    }
}
