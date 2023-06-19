package com.rojek.dietplanner.repository;

import com.rojek.dietplanner.entity.Meal;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long>, PagingAndSortingRepository<Meal, Long> {
    List<Meal> findAllByNameContaining(String name);

    @NonNull Page<Meal> findAll(@NonNull Pageable pageable);
}
