package org.sevenorganization.int20h2023ttbe.domain.dto;

import java.util.List;

public record RecipeDto(
        List<String> steps,
        int complexity
) {
}
