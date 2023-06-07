package com.rojek.dietplanner.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity()
@Table(name = "meals")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meal {
    @Id
    @GeneratedValue
    private Long mealId;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer calories;
    @Column(nullable = false)
    private Double proteins;
    @Column(nullable = false)
    private Double fats;
    @Column(nullable = false)
    private Double carbohydrates;
    @Column(nullable = false)
    private Boolean isPublic;
}
