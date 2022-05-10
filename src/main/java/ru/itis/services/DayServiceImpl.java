package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Day;
import ru.itis.models.User;
import ru.itis.repositories.DayRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DayServiceImpl implements DayService{

    @Autowired
    private DayRepository dayRepository;

    @Override
    public void deleteAllByUserId(Long userId) {
        User user = User.builder()
                .id(userId)
                .build();
        dayRepository.deleteAllByUserId(user);
    }

    @Override
    public Day findDayByDateAndUser(LocalDate date, User user) {
        return dayRepository.findDayByDateAndUser(date, user);
    }

    @Override
    public Day findDayById(Long id) {
        return dayRepository.findById(id).get();
    }

    @Override
    public List<Day> getAllByUserId(User user) {
        return dayRepository.getAllByUserId(user);
    }

    @Override
    public boolean isDayExists(LocalDate date, User userId) {
       return dayRepository.existsDayByDateAndUserId(date, userId);
    }

    @Override
    public Day createDay(LocalDate date, User user) {
       Day day = Day.builder()
               .date(date)
               .user(user)
               .burnedCalories((float) 0)
               .calories((float) 0)
               .carbs((float) 0)
               .fiber((float) 0)
               .protein((float) 0)
               .build();
       dayRepository.save(day);
       return day;
    }

    @Override
    public void addCalories(Long dayId, Float calories) {
        dayRepository.updateCaloriesByDayId(dayId, calories);
    }

    @Override
    public void addBurnedCalories(Long dayId, Float burnedCalories) {
        Day day = findDayById(dayId);
        Float oldBurnedCalories = day.getBurnedCalories();
        if (oldBurnedCalories == null) {
            dayRepository.updateBurnedCaloriesByDayId(dayId, burnedCalories);
        } else {
            dayRepository.updateBurnedCaloriesByDayId(dayId, burnedCalories + day.getBurnedCalories());
        }
        //day.ifPresent(value -> dayRepository.updateBurnedCaloriesByDayId(dayId, burnedCalories + value.getBurnedCalories()));
    }
}
