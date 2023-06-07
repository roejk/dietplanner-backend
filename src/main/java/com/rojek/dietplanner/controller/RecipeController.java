package com.rojek.dietplanner.controller;

import com.rojek.dietplanner.dto.RecipeDTO;
import com.rojek.dietplanner.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/recipes")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/recipes/{name}")
    public ResponseEntity<List<RecipeDTO>> getRecipesByName(@PathVariable String name) {
        return ResponseEntity.ok(recipeService.getRecipesByName(name));
    }

    @GetMapping("/recipe/{mealId}")
    public ResponseEntity<RecipeDTO> getRecipeByMealId(@PathVariable Long mealId) {
        return ResponseEntity.ok(recipeService.getRecipeByMealId(mealId));
    }

    @PostMapping("/recipe/add")
    public ResponseEntity<RecipeDTO> addRecipe(@RequestBody RecipeDTO recipeDTO) {
        return ResponseEntity.ok(recipeService.addRecipe(recipeDTO));
    }
}
