package org.sevenorganization.int20h2023ttbe.service.helper.meal;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.service.IngredientService;
import org.sevenorganization.int20h2023ttbe.service.helper.meal.mapper.DefaultMealMapper;
import org.sevenorganization.int20h2023ttbe.service.helper.meal.mapper.IngredientMeasureMealMapper;
import org.sevenorganization.int20h2023ttbe.service.helper.meal.mapper.RecipeMealMapper;
import org.sevenorganization.int20h2023ttbe.service.helper.meal.mapper.TagMealMapper;
import org.springframework.stereotype.Component;

import static org.sevenorganization.int20h2023ttbe.service.helper.meal.MealHelper.*;

@Component
@RequiredArgsConstructor
public class MealRetriever {

    private final IngredientService ingredientService;

    public MealMapper extractMealMapper(String key) {
        if (key.equals(STR_INSTRUCTIONS)) {
            return new RecipeMealMapper();
        } else if (key.startsWith(STR_TAGS)) {
            return new TagMealMapper();
        } else if (key.startsWith(STR_INGREDIENT) || key.startsWith(STR_MEASURE)) {
            return new IngredientMeasureMealMapper(ingredientService);
        }
        return new DefaultMealMapper();
    }

}
