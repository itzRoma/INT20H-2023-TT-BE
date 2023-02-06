package org.sevenorganization.int20h2023ttbe.feign;

import org.sevenorganization.int20h2023ttbe.domain.dto.IngredientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "IngredientFeignClient", url = "${the-meal-db.base-url}")
public interface IngredientFeignClient {
    @GetMapping("/list.php?i=list")
    Map<String, List<IngredientDto>> getAllIngredients();
}
