package org.sevenorganization.int20h2023ttbe.service.helper.meal;

import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;

public interface MealMapper {
    void mapAttrKey(String key, String value, MealDto mealDto);
}
