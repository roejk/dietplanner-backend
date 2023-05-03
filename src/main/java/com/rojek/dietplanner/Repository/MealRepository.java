package com.rojek.dietplanner.Repository;

import com.rojek.dietplanner.Entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByName(String name);
    Optional<Meal> findByIsPublic(Boolean isPublic);
}
