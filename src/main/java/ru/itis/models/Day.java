package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "day")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private java.time.LocalDate date;

    @OneToOne
    private User user;

    private Float burnedCalories;

    private Float calories;

    private Float protein;

    private Float fiber;

    private Float carbs;

    @ManyToMany
    @JoinTable()
    private List<Product> product;

    @OneToMany(mappedBy = "day")
    private List<ExerciseAdding> exercise;
}
