package com.rojek.dietplanner.controller;

import com.rojek.dietplanner.dto.MealDTO;
import com.rojek.dietplanner.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/meals")
    public ResponseEntity<List<MealDTO>> getMeals() {
        return ResponseEntity.ok(mealService.getMeals());
    }

    @GetMapping("/meals/{name}")
    public ResponseEntity<List<MealDTO>> getMealsByName(@PathVariable String name) {
        return ResponseEntity.ok(mealService.getMealsByName(name));
    }

    @PostMapping("/meal/add")
    public ResponseEntity<MealDTO> addMeal(@RequestBody MealDTO mealDTO) {
        return ResponseEntity.ok(mealService.addMeal(mealDTO));
    }
}
