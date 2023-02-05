package org.sevenorganization.int20h2023ttbe.service.helper.meal;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.MealDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
@Component
public final class MealHelper {

    private final MealRetriever mealRetriever;
    public static final String STR_INSTRUCTIONS = "strInstructions";
    public static final String STR_TAGS = "strTags";
    public static final String STR_INGREDIENT = "strIngredient";
    public static final String STR_MEASURE = "strMeasure";

    public List<MealDto> retrieveMealsFromResponse(List<List<Map<String, String>>> values) {
        if (nonNull(values.get(0))) {
            return values.get(0).stream()
                    .map(Map::entrySet)
                    .map(this::createMealDto)
                    .toList();
        }
        return Collections.emptyList();
    }

    private MealDto createMealDto(Set<Map.Entry<String, String>> entries) {
        MealDto mealDto = new MealDto();
        entries.forEach(stringStringEntry -> setMealDtoAttribute(stringStringEntry, mealDto));
        return mealDto;
    }

    private void setMealDtoAttribute(Map.Entry<String, String> stringStringEntry, MealDto mealDto) {
        String key = stringStringEntry.getKey();
        String value = stringStringEntry.getValue();
        MealMapper mealMapper = mealRetriever.extractMealMapper(key);
        mealMapper.mapAttrKey(key, value, mealDto);
    }
}
