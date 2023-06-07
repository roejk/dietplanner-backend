package com.rojek.dietplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private Long recipeId;
    private String name;
    private String ingredients;
    private String instructions;
    private Long mealId;
}
