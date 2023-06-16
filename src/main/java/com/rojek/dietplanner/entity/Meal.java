package com.rojek.dietplanner.entity;

import com.opencsv.bean.CsvBindByName;
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
    @CsvBindByName
    @Column(unique = true, nullable = false)
    private String name;
    @CsvBindByName
    @Column(nullable = false)
    private Integer calories;
    @CsvBindByName
    @Column(nullable = false)
    private Double proteins;
    @CsvBindByName
    @Column(nullable = false)
    private Double fats;
    @CsvBindByName
    @Column(nullable = false)
    private Double carbohydrates;
    @Builder.Default
    @Column(nullable = false)
    private Boolean isPublic = true;
}
