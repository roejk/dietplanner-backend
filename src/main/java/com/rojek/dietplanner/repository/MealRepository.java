package com.rojek.dietplanner.repository;

import com.rojek.dietplanner.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByNameContaining(String name);
    Meal findByIsPublic(Boolean isPublic);
}
