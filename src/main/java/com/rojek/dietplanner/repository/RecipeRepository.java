package com.rojek.dietplanner.repository;

import com.rojek.dietplanner.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByNameContaining(String name);
    List<Recipe> findAllByMealMealId(Long id);
}
