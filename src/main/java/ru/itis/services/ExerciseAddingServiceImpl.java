package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dtos.ExerciseDto;
import ru.itis.dtos.ExerciseResponseDto;
import ru.itis.models.Day;
import ru.itis.models.Exercise;
import ru.itis.models.ExerciseAdding;
import ru.itis.repositories.ExerciseAddingRepository;

import java.util.List;

@Service
public class ExerciseAddingServiceImpl implements ExerciseAddingService{

    @Autowired
    private ExerciseAddingRepository exerciseAddingRepository;

    @Override
    public ExerciseResponseDto addExercise(Day day, ExerciseDto dto, Exercise exercise) {
        ExerciseResponseDto exerciseResponseDto = ExerciseResponseDto.builder()
                .kind(exercise.getKind())
                .duration(dto.getDuration())
                .build();
        ExerciseAdding exerciseAdding = ExerciseAdding.builder()
                .exercise(exercise)
                .day(day)
                .duration(dto.getDuration())
                .build();
        exerciseAddingRepository.save(exerciseAdding);
        return exerciseResponseDto;
    }

    @Override
    public List<ExerciseAdding> getExerciseByDay(Day day) {
        return exerciseAddingRepository.findExerciseAddingByDay(day);
    }
}
