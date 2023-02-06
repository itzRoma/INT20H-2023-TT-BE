package org.sevenorganization.int20h2023ttbe.service.helper.meal.mapper;

import lombok.extern.slf4j.Slf4j;
import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.sevenorganization.int20h2023ttbe.service.helper.meal.MealMapper;

import java.lang.reflect.Field;

@Slf4j
public class DefaultMealMapper implements MealMapper {
    @Override
    public void mapAttrKey(String key, String value, MealDto mealDto) {
        try {
            Field field = mealDto.getClass().getDeclaredField(key);
            field.setAccessible(true);
            field.set(mealDto, value);
        } catch (NoSuchFieldException e) {
            log.debug(e.getMessage()+"attr was not mapped");
        } catch (IllegalAccessException e) {
            log.error(e.getMessage()+"some error");
        }
    }
}
