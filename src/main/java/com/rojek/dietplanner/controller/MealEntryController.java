package com.rojek.dietplanner.controller;

import com.rojek.dietplanner.dto.MealEntryDTO;
import com.rojek.dietplanner.dto.MealEntryResponseDTO;
import com.rojek.dietplanner.service.MealEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class MealEntryController {

    private final MealEntryService mealEntryService;

    @GetMapping("/entries/{username}")
    public ResponseEntity<List<MealEntryResponseDTO>> getUserMealEntries(@PathVariable String username) {
        return ResponseEntity.ok(mealEntryService.getUserMealEntries(username));
    }

    @GetMapping("/entries/{username}/{date}")
    public ResponseEntity<List<MealEntryResponseDTO>> getUserMealEntriesByDate(
            @PathVariable String username, @PathVariable String date) {
        return ResponseEntity.ok(mealEntryService.getUserMealEntriesByDate(username, date));
    }

    @PostMapping("/entry/add")
    public ResponseEntity<MealEntryDTO> addMealEntry(@RequestBody MealEntryDTO mealEntryDTO) {
        return ResponseEntity.ok(mealEntryService.addMealEntry(mealEntryDTO));
    }
}
