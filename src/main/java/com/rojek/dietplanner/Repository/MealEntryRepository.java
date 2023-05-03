package com.rojek.dietplanner.Repository;

import com.rojek.dietplanner.Entity.MealEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MealEntryRepository extends JpaRepository<MealEntry, Long> {
    Optional<MealEntry> findByDate(Date date);
}
