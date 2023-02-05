package org.sevenorganization.int20h2023ttbe.service.helper.meal.mapper;

import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.service.helper.meal.MealMapper;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class TagMealMapper implements MealMapper {

    private static final String REGEX = ",";

    @Override
    public void mapAttrKey(String key, String value, MealDto mealDto) {
        if (nonNull(value)) {
            mealDto.setStrTags(Arrays.stream(value.split(REGEX)).collect(Collectors.toSet()));
        }
    }
}
