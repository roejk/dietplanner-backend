package com.rojek.dietplanner.service;

import com.rojek.dietplanner.dto.MealEntryDTO;
import com.rojek.dietplanner.dto.MealEntryResponseDTO;
import com.rojek.dietplanner.entity.MealEntry;
import com.rojek.dietplanner.helper.MapHelper;
import com.rojek.dietplanner.repository.MealEntryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealEntryService {

    private final MealEntryRepository mealEntryRepository;
    private final MapHelper mapHelper;

    public List<MealEntryResponseDTO> getUserMealEntries(String username) {
        List<MealEntry> mealEntries = mealEntryRepository.findAllByUserUsername(username);
        return mapHelper.mapMealEntriesToDTO(mealEntries);
    }

    public List<MealEntryResponseDTO> getUserMealEntriesByDate(String username, String stringDate) {
        List<MealEntry> mealEntries = mealEntryRepository
                .findAllByUserUsernameAndDate(username, LocalDate.parse(stringDate));
        return mapHelper.mapMealEntriesToDTO(mealEntries);
    }

    @Transactional
    public MealEntryDTO addMealEntry(MealEntryDTO mealEntryDTO) {
        MealEntry newEntry = mapHelper.getMealEntryFromDTO(mealEntryDTO);
        mealEntryRepository.save(newEntry);

        return mealEntryDTO;
    }
}
