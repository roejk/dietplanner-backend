package com.rojek.dietplanner.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rojek.dietplanner.dto.MealDTO;
import com.rojek.dietplanner.entity.Meal;
import com.rojek.dietplanner.helper.MapHelper;
import com.rojek.dietplanner.repository.MealRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
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

    public Page<MealDTO> getMeals(int page, int size) {
        Page<Meal> meals = mealRepository.findAll(PageRequest.of(page, size));
        List<MealDTO> mealDTOList = new ArrayList<>();
        meals.forEach(meal -> mealDTOList.add(mapHelper.mapMealIdToDTO(meal.getMealId())));
        return new PageImpl<>(mealDTOList, meals.getPageable(), meals.getTotalElements());
    }

    @Transactional
    public MealDTO addMeal(MealDTO mealDTO) {
        Meal meal = mapHelper.getMealFromDTO(mealDTO);
        mealRepository.save(meal);

        return mealDTO;
    }

    public Integer addMealsFromCSV(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<Meal> csvToBean = new CsvToBeanBuilder<Meal>(reader)
                    .withType(Meal.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Meal> meals = csvToBean.parse();
            mealRepository.saveAll(meals);

        } catch (Exception ex) {
            return -1;
        }
        return 200;
    }
}
