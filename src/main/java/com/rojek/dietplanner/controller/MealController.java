package com.rojek.dietplanner.controller;

import com.rojek.dietplanner.dto.MealDTO;
import com.rojek.dietplanner.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(value = "/meals", params = { "page", "size" })
    public ResponseEntity<Page<MealDTO>> getMealsPageable(
            @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(mealService.getMeals(page, size));
    }

    @GetMapping(value = "/meals", params = "name")
    public ResponseEntity<List<MealDTO>> getMealsByName(@RequestParam String name) {
        return ResponseEntity.ok(mealService.getMealsByName(name));
    }

    @PostMapping("/meal/add")
    public ResponseEntity<MealDTO> addMeal(@RequestBody MealDTO mealDTO) {
        return ResponseEntity.ok(mealService.addMeal(mealDTO));
    }

    @PostMapping("/meal/add/csv")
    public ResponseEntity<Integer> addMealsFromCSV(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(mealService.addMealsFromCSV(file));
    }
}
