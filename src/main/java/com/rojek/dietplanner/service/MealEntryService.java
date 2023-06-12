package com.rojek.dietplanner.service;

import com.rojek.dietplanner.dto.MealEntryDTO;
import com.rojek.dietplanner.dto.MealEntryResponseDTO;
import com.rojek.dietplanner.entity.MealEntry;
import com.rojek.dietplanner.helper.MapHelper;
import com.rojek.dietplanner.repository.MealEntryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Long deleteMealEntry(Long mealEntryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<MealEntry> entry = mealEntryRepository.findById(mealEntryId);

        if (entry.isPresent()) {
            MealEntry mealEntry = entry.get();
            if (Objects.equals(mealEntry.getUser().getUsername(), currentPrincipalName)) {
                mealEntryRepository.delete(entry.get());
                return mealEntryId;
            } else return (long) -403;
        } else return (long) -404;
    }
}
