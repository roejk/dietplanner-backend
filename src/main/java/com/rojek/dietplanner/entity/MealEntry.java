package com.rojek.dietplanner.entity;

import com.rojek.dietplanner.type.MealEntryType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity()
@Table(name = "meal_entries")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealEntry {
    @Id
    @GeneratedValue
    private Long mealEntryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private MealEntryType type;
}
