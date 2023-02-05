package org.sevenorganization.int20h2023ttbe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.feign.MealFeignClient;
import org.sevenorganization.int20h2023ttbe.service.helper.meal.MealHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealService {

    private final MealFeignClient mealFeignClient;
    private final MealHelper mealHelper;

    public MealDto getMealByExternalId(Long externalId) {
        var response = mealFeignClient.getMealByExternalId(externalId).values().stream().toList();
        return mealHelper.retrieveMealsFromResponse(response).stream()
                .findAny().orElse(null);
    }

    public List<MealDto> getMealByFirstLetter(String firstLetter) {
        var response = mealFeignClient.getMealByFirstLetter(firstLetter).values().stream().toList();
        return mealHelper.retrieveMealsFromResponse(response);
    }
}
