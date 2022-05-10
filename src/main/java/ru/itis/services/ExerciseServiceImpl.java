package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Exercise;
import ru.itis.repositories.ExerciseRepository;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService{

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public List<Exercise> getAllExercise() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise getById(Long id) {
        return exerciseRepository.getById(id);
    }
}
