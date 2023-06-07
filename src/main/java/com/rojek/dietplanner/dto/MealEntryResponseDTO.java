package com.rojek.dietplanner.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rojek.dietplanner.type.MealEntryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealEntryResponseDTO {
    private Long mealEntryId;
    private String username;
    private MealDTO meal;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Double amount;
    private MealEntryType type;
}
