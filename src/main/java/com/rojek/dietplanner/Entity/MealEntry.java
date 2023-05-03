package com.rojek.dietplanner.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealEntryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private MealEntryType type;
}
