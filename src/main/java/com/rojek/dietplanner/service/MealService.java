package com.rojek.dietplanner.service;

import com.rojek.dietplanner.dto.MealDTO;
import com.rojek.dietplanner.entity.Meal;
import com.rojek.dietplanner.helper.MapHelper;
import com.rojek.dietplanner.repository.MealRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final MapHelper mapHelper;

    public List<MealDTO> getMeals() {
        List<Meal> meals = mealRepository.findAll();
        return meals.stream()
                .map(meal -> mapHelper.mapMealIdToDTO(meal.getMealId()))
                .toList();
    }

    public List<MealDTO> getMealsByName(String name) {
        List<Meal> meals = mealRepository.findAllByNameContaining(name);
        return meals.stream()
                .map(meal -> mapHelper.mapMealIdToDTO(meal.getMealId()))
                .toList();
    }

    @Transactional
    public MealDTO addMeal(MealDTO mealDTO) {
        Meal meal = mapHelper.getMealFromDTO(mealDTO);
        mealRepository.save(meal);

        return mealDTO;
    }
}
