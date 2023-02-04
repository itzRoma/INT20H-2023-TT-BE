package org.sevenorganization.int20h2023ttbe.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "MealFeignClient", url = "${the-meal-db.base-url}")
public interface MealFeignClient {
    @GetMapping(value = "/lookup.php", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, List<Map<String, String>>> getMealByExternalId(@RequestParam("i") Long externalId);
}
