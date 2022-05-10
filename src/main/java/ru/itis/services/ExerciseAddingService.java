package ru.itis.services;

import ru.itis.dtos.ExerciseDto;
import ru.itis.dtos.ExerciseResponseDto;
import ru.itis.models.Day;
import ru.itis.models.Exercise;
import ru.itis.models.ExerciseAdding;

import java.util.List;

public interface ExerciseAddingService {
    ExerciseResponseDto addExercise(Day day, ExerciseDto dto, Exercise exercise);
    List<ExerciseAdding> getExerciseByDay(Day day);
}
