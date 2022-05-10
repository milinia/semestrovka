package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dtos.RegistrationDto;
import ru.itis.exception.UserAlreadyExistException;
import ru.itis.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/signUp")
    public ModelAndView signUpPage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies){
                if (cookie.getName().equals("Auth")) {
                    return new ModelAndView("redirect:/profile");
                }
            }
        }
        return new ModelAndView("signUpPage");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signUp")
    public ModelAndView signUpUser(ModelAndView modelAndView, RegistrationDto registrationDto) {
        try {
            userService.signUp(registrationDto);
        } catch (UserAlreadyExistException e) {
            modelAndView.addObject("error", "User with such email already exist!");
            modelAndView.setViewName("signUpPage");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/signIn");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/signIn")
    public ModelAndView getLoginForm(HttpServletRequest request, ModelMap model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies){
                if (cookie.getName().equals("Auth")) {
                    return new ModelAndView("redirect:/profile");
                }
            }
        }
        if (request.getParameter("error") != null) {
            model.addAttribute("error", "Wrong email or password");
        }
        return new ModelAndView("loginPage");
    }
}
