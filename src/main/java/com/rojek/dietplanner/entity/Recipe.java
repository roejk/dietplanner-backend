package com.rojek.dietplanner.entity;

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
    @GeneratedValue
    private Long recipeId;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "text")
    private String ingredients;
    @Column(nullable = false, columnDefinition = "text")
    private String instructions;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;
}
