package com.rojek.dietplanner.repository;

import com.rojek.dietplanner.entity.MealEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealEntryRepository extends JpaRepository<MealEntry, Long> {

    List<MealEntry> findAllByUserUsername(String username);
    List<MealEntry> findAllByUserUsernameAndDate(String username, LocalDate date);
}
