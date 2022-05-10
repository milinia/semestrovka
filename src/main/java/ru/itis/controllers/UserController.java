package ru.itis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.*;
import ru.itis.services.*;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DayService dayService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ExerciseAddingService exerciseAddingService;

    @Autowired
    private ExerciseService exerciseService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public ModelAndView getProfilePage(){
        ModelAndView modelAndView = new ModelAndView();
        LocalDate today = LocalDate.now();
        modelAndView.setViewName("redirect:/profile/" + today);
        return modelAndView;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/profile/{date}")
    public ModelAndView getProfilePage(@PathVariable String date, Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        LocalDate localDate = LocalDate.parse(date);
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            Day day = dayService.findDayByDateAndUser(localDate, user);
            if (day != null) {
                List<ExerciseAdding> exerciseList = exerciseAddingService.getExerciseByDay(day);
                List<Exercise> exercises = exerciseService.getAllExercise();
                if (exerciseList != null)
                    modelAndView.addObject("exerciseList", exerciseList);
                if (exercises != null)
                    modelAndView.addObject("exercises", exercises);
                modelAndView.setViewName("profilePage");
                modelAndView.addObject("day", day);
                return modelAndView;
            } else {
                Day newDay = dayService.createDay(localDate, user);
                modelAndView.addObject("day", newDay);
                modelAndView.setViewName("profilePage");
                return modelAndView;
            }
        } else {
            modelAndView.setViewName("loginPage");
            return modelAndView;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public String deleteUser(HttpServletRequest request) {
        Long id = Long.parseLong(request.getSession().getAttribute("id").toString());
        userService.delete(id);
        return "redirect:/mainPage";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/change_password")
    public String changeUserPassword(@RequestParam String newPassword, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies){
                if (cookie.getName().equals("Auth")) {
                    Optional<Auth> auth = authService.getAuthByCookieValue(cookie.getValue());
                    auth.ifPresent(value -> userService.updatePasswordById(value.getUser().getId(), newPassword));
                }
            }
        }
        return "redirect:/signIn";
    }
}
