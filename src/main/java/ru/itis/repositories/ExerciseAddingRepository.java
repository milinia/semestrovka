package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Day;
import ru.itis.models.ExerciseAdding;

import java.util.List;

@Repository
public interface ExerciseAddingRepository extends JpaRepository<ExerciseAdding, Long> {
    List<ExerciseAdding> findExerciseAddingByDay(Day day);
}
