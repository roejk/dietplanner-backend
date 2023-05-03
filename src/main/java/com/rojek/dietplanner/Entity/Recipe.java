package com.rojek.dietplanner.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity()
@Table(name = "recipes")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String ingredients;
    @Column(nullable = false)
    private String instructions;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;
}
