package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.models.Day;
import ru.itis.models.User;
import ru.itis.repositories.DayRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface DayService {

   void deleteAllByUserId(Long userId);

    Day findDayByDateAndUser(LocalDate date, User user);

    Day findDayById(Long id);

    List<Day> getAllByUserId(User user);

    boolean isDayExists(LocalDate date, User userId);

    Day createDay(LocalDate date, User user);

    void addCalories(Long dayId, Float calories);

    void addBurnedCalories(Long dayId, Float burnedCalories);
}
