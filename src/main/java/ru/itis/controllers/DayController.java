package ru.itis.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dtos.ExerciseDto;
import ru.itis.dtos.ExerciseResponseDto;
import ru.itis.dtos.ProductDto;
import ru.itis.models.Day;
import ru.itis.models.Exercise;
import ru.itis.services.DayService;
import ru.itis.services.ExerciseAddingService;
import ru.itis.services.ExerciseService;

@Transactional
@Controller()
public class DayController {

    @Autowired
    private ExerciseAddingService exerciseAddingService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private DayService dayService;

//    @RequestMapping(method = RequestMethod.POST, value = "/add_product")
//    public ModelAndView addProduct(ProductDto productDto){
//
//    }
    @RequestMapping(method = RequestMethod.POST, value = "/add_exercise", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addExercise(@RequestBody() ExerciseDto exerciseDto) throws JsonProcessingException {
        Exercise exercise = exerciseService.getById(Long.parseLong(exerciseDto.getKind()));
        Day day = dayService.findDayById(Long.parseLong(exerciseDto.getId()));
        ObjectMapper objectMapper = new ObjectMapper();
        if (exercise != null & day != null) {
            Float burnedCalories = exerciseDto.getDuration() * exercise.getEnergyBurnedForMin();
            ExerciseResponseDto dto = exerciseAddingService.addExercise(day, exerciseDto, exercise);
            dayService.addBurnedCalories(day.getId(), burnedCalories);
            return objectMapper.writeValueAsString(dto);
        }
        return objectMapper.writeValueAsString("");
    }
}
