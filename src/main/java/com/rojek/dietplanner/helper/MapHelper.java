package com.rojek.dietplanner.helper;

import com.rojek.dietplanner.dto.*;
import com.rojek.dietplanner.entity.Meal;
import com.rojek.dietplanner.entity.MealEntry;
import com.rojek.dietplanner.entity.Recipe;
import com.rojek.dietplanner.entity.User;
import com.rojek.dietplanner.repository.MealRepository;
import com.rojek.dietplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MapHelper {

    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    public List<MealEntryResponseDTO> mapMealEntriesToDTO(List<MealEntry> mealEntries) {
        List<MealEntryResponseDTO> responseList = new ArrayList<>();
        mealEntries.forEach(entry -> {
            MealEntryResponseDTO mealEntryDTO = MealEntryResponseDTO.builder()
                    .mealEntryId(entry.getMealEntryId())
                    .meal(mapMealIdToDTO(entry.getMeal().getMealId()))
                    .amount(entry.getAmount())
                    .date(entry.getDate())
                    .type(entry.getType())
                    .username(entry.getUser().getUsername())
                    .build();
            responseList.add(mealEntryDTO);
        });

        return responseList;
    }

    public MealDTO mapMealIdToDTO(Long mealId) {
        Optional<Meal> meal = mealRepository.findById(mealId);
        MealDTO mealDTO;

        if (meal.isPresent()) {
            Meal obj = meal.get();
            mealDTO = MealDTO.builder()
                    .mealId(obj.getMealId())
                    .name(obj.getName())
                    .calories(obj.getCalories())
                    .proteins(obj.getProteins())
                    .fats(obj.getFats())
                    .carbohydrates(obj.getCarbohydrates())
                    .isPublic(obj.getIsPublic())
                    .build();
        } else {
            mealDTO = MealDTO.builder().name("Invalid meal").build(); //todo: ??
        }
        return mealDTO;
    }

    public MealEntry getMealEntryFromDTO(MealEntryDTO mealEntryDTO) {
        Optional<User> user = userRepository.findByUsername(mealEntryDTO.getUsername());
        Optional<Meal> mealOptional = mealRepository.findById(mealEntryDTO.getMealId());

        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            return user.map(value -> MealEntry.builder()
                    .mealEntryId(mealEntryDTO.getMealEntryId())
                    .amount(mealEntryDTO.getAmount())
                    .meal(meal)
                    .date(mealEntryDTO.getDate())
                    .type(mealEntryDTO.getType())
                    .user(value)
                    .build()).orElse(null); //todo: ??
        } else return null;
    }

    public Meal getMealFromDTO(MealDTO mealDTO) {
        return Meal.builder()
                .mealId(mealDTO.getMealId())
                .name(mealDTO.getName())
                .calories(mealDTO.getCalories())
                .proteins(mealDTO.getProteins())
                .fats(mealDTO.getFats())
                .carbohydrates(mealDTO.getCarbohydrates())
                .isPublic(mealDTO.getIsPublic())
                .build();
    }

    public RecipeDTO mapRecipeToDTO(Recipe recipe) {
        return RecipeDTO.builder()
                .recipeId(recipe.getRecipeId())
                .mealId(recipe.getMeal().getMealId())
                .name(recipe.getName())
                .ingredients(recipe.getIngredients())
                .instructions(recipe.getInstructions())
                .build();
    }

    public Recipe getRecipeFromDTO(RecipeDTO recipeDTO) {
        Optional<Meal> mealOptional = mealRepository.findById(recipeDTO.getMealId());

        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            return Recipe.builder()
                    .name(recipeDTO.getName())
                    .ingredients(recipeDTO.getIngredients())
                    .instructions(recipeDTO.getInstructions())
                    .meal(meal)
                    .build();
        } else return null;
    }

    public List<UserDTO> mapUsersToDTO(List<User> users) {
        List<UserDTO> responseList = new ArrayList<>();
        users.forEach(entry -> {
            UserDTO userDTO = UserDTO.builder()
                    .userId(entry.getUserId())
                    .username(entry.getUsername())
                    .email(entry.getEmail())
                    .role(entry.getUserRole())
                    .isLocked(entry.getIsLocked())
                    .isEnabled(entry.getIsEnabled())
                    .build();
            responseList.add(userDTO);
        });

        return responseList;
    }
}
