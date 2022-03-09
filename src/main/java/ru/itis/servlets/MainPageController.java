package ru.itis.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {

    @RequestMapping(method = RequestMethod.GET, value = "/main")
    public ModelAndView getMainPage(){
        return new ModelAndView("mainPage");
    }
}
