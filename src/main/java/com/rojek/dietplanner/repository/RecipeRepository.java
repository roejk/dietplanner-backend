package com.rojek.dietplanner.repository;

import com.rojek.dietplanner.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByNameContaining(String name);
    Optional<Recipe> findByMealMealId(Long id);
}
