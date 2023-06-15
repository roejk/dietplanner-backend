package com.rojek.dietplanner.service;

import com.rojek.dietplanner.dto.RecipeDTO;
import com.rojek.dietplanner.entity.Recipe;
import com.rojek.dietplanner.helper.MapHelper;
import com.rojek.dietplanner.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {


    private final RecipeRepository recipeRepository;
    private final MapHelper mapHelper;


    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        recipes.forEach(recipe -> recipeDTOList.add(mapHelper.mapRecipeToDTO(recipe)));

        return recipeDTOList;
    }

    public List<RecipeDTO> getRecipesByName(String name) {
        List<Recipe> recipes = recipeRepository.findAllByNameContaining(name);

        return recipes.stream()
                .map(mapHelper::mapRecipeToDTO)
                .toList();
    }

    public List<RecipeDTO> getRecipesByMealId(Long mealId) {
        List<Recipe> recipes = recipeRepository.findAllByMealMealId(mealId);

        return recipes.stream()
                .map(mapHelper::mapRecipeToDTO)
                .toList();
    }

    public RecipeDTO addRecipe(RecipeDTO recipeDTO) {
        Recipe newRecipe = mapHelper.getRecipeFromDTO(recipeDTO);
        recipeRepository.save(newRecipe);

        return recipeDTO;
    }


}
