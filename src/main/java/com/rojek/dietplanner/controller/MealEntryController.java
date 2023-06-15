package com.rojek.dietplanner.controller;

import com.rojek.dietplanner.dto.MealEntryDTO;
import com.rojek.dietplanner.dto.MealEntryResponseDTO;
import com.rojek.dietplanner.service.MealEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class MealEntryController {

    private final MealEntryService mealEntryService;

    @PreAuthorize("#username == authentication.name")
    @GetMapping(value = "/entries", params = "username")
    public ResponseEntity<List<MealEntryResponseDTO>> getUserMealEntries(@RequestParam("username") String username) {
        return ResponseEntity.ok(mealEntryService.getUserMealEntries(username));
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping(value = "/entries", params = {"username", "date"})
    public ResponseEntity<List<MealEntryResponseDTO>> getUserMealEntriesByDate(
            @RequestParam("username") String username, @RequestParam("date") String date) {
        return ResponseEntity.ok(mealEntryService.getUserMealEntriesByDate(username, date));
    }

    @PreAuthorize("#mealEntryDTO.username == authentication.name")
    @PostMapping("/entry/add")
    public ResponseEntity<MealEntryDTO> addMealEntry(@RequestBody MealEntryDTO mealEntryDTO) {
        return ResponseEntity.ok(mealEntryService.addMealEntry(mealEntryDTO));
    }

    @PostAuthorize("returnObject != -403")
    @DeleteMapping("/entry/delete/{mealEntryId}")
    public ResponseEntity<Long> deleteMealEntry(@PathVariable Long mealEntryId) {
        return ResponseEntity.ok(mealEntryService.deleteMealEntry(mealEntryId));

    }
}
