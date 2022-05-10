package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.models.Day;
import ru.itis.models.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {

    Day findDayByDateAndUser(LocalDate date, User user);
    void deleteAllByUserId(User user);
    List<Day> getAllByUserId(User user);
    boolean existsDayByDateAndUserId(LocalDate date, User userId);

    @Modifying
    @Query(value = "update Day d set d.calories = ?2 where d.id = ?1")
    void updateCaloriesByDayId(Long id, Float calories);

    @Modifying
    @Query(value = "update Day d set d.burnedCalories = ?2 where d.id = ?1")
    void updateBurnedCaloriesByDayId(Long id, Float calories);



}
