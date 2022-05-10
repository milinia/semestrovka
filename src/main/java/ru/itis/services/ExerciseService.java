package ru.itis.services;

import ru.itis.models.Exercise;
import ru.itis.repositories.ExerciseRepository;

import java.util.List;

public interface ExerciseService {

    List<Exercise> getAllExercise();
    Exercise getById(Long id);
}
