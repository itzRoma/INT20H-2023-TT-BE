package org.sevenorganization.int20h2023ttbe.domain.dto;

import java.util.Set;

public record RecipeDto(
        Set<String> steps,
        int complexity
) {
}
