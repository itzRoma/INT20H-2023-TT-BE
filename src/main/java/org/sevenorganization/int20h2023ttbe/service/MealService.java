package org.sevenorganization.int20h2023ttbe.service;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.feign.MealFeignClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealFeignClient mealFeignClient;

    public MealDto getMealByExternalId(Long externalId) {
        return null;
    }
}
