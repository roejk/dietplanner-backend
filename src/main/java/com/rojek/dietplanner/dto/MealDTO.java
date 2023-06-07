package com.rojek.dietplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealDTO {
    private Long mealId;
    private String name;
    private Integer calories;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
    private Boolean isPublic;
}
