package org.sevenorganization.int20h2023ttbe.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Get all ingredients", notes = "By calling this endpoint all ingredients will be returned.")
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/name/{name}")
    @ApiOperation(
            value = "Get ingredient by name",
            notes = "By calling this endpoint an ingredient that matches the provided name will be returned."
    )
    public ResponseEntity<IngredientDto> getIngredientByName(
            @ApiParam(value = "Ingredient name", name = "name", type = "String", required = true)
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(ingredientService.getIngredientByName(name).get());
    }

    @GetMapping("/c-name/{name}")
    @ApiOperation(
            value = "Get ingredients by name containing",
            notes = "By calling this endpoint an ingredient that name contains the provided name will be returned."
    )
    public ResponseEntity<List<IngredientDto>> getIngredientsByNameContaining(
            @ApiParam(value = "Ingredient name", name = "name", type = "String", required = true)
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(ingredientService.getIngredientByNameContaining(name));
    }

    @GetMapping("/type/{type}")
    @ApiOperation(
            value = "Get ingredient by type",
            notes = "By calling this endpoint an ingredient that matches the provided type will be returned."
    )
    public ResponseEntity<List<IngredientDto>> getIngredientsByType(
            @ApiParam(value = "Ingredient type", name = "type", type = "String", required = true)
            @PathVariable("type") String type
    ) {
        return ResponseEntity.ok(ingredientService.getIngredientsByType(type));
    }


    @GetMapping("/type/{type}/name/{name}")
    @ApiOperation(
            value = "Get ingredient by type and name",
            notes = "By calling this endpoint an ingredient that matches the provided type and name will be returned."
    )
    public ResponseEntity<List<IngredientDto>> getIngredientsByTypeAndName(
            @ApiParam(value = "Ingredient type", name = "type", type = "String", required = true)
            @PathVariable("type") String type,
            @ApiParam(value = "Ingredient name", name = "name", type = "String", required = true)
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(ingredientService.getIngredientsByTypeAndName(type, name));
    }

    @GetMapping("/types")
    @ApiOperation(
            value = "Get all ingredients types",
            notes = "By calling this endpoint all the ingredients types will be returned."
    )
    public ResponseEntity<List<String>> getAllIngredientsTypes() {
        return ResponseEntity.ok(ingredientService.getAllIngredientsTypes());
    }
}
