package ru.itis.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.Subscription;
import ru.itis.models.User;
import ru.itis.services.SubscriptionService;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public ModelAndView getProfilePage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("profile");
        Long id = Long.parseLong(request.getSession().getAttribute("id").toString());
        List<Subscription> subscriptionList = subscriptionService.getSubscriptionsByUserId(id);
        modelAndView.addObject("subscriptionList", subscriptionList);
        return modelAndView;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("id");
        return "redirect:/signup";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView getLoginPage(){
        return new ModelAndView("loginPage");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ModelAndView login(HttpServletRequest request){
        User user = userService.findUserByEmailAndPassword(request.getParameter("email"), request.getParameter("password"));
        if (user != null)
            request.getSession().setAttribute("id", user.getId());
        else {
            ModelAndView modelAndView = new ModelAndView("loginPage");
            modelAndView.addObject("message", "User with such email and password doesn't exist");
            return  modelAndView;
        }
        return new ModelAndView("redirect:/profile");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public String deleteUser(HttpServletRequest request){
        Long id = Long.parseLong(request.getSession().getAttribute("id").toString());
        userService.delete(id);
        subscriptionService.getSubscriptionsByUserId(id);
        request.getSession().removeAttribute("id");
        return "redirect:/main";
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/change_password")
    public String changeUserPassword(HttpServletRequest request, @RequestParam String newPassword){
        Long id = Long.parseLong(request.getSession().getAttribute("id").toString());
        //hashing password
        userService.updatePassword(id, newPassword);
        return "redirect:/profile";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/signup")
    public ModelAndView getSignupPage() {
        return new ModelAndView("signUpPage");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ModelAndView signup(HttpServletRequest request) {
        //hashing password
        User user = User.builder()
                .name(request.getParameter("name"))
                .email(request.getParameter("email"))
                .surname(request.getParameter("surname"))
                .password(request.getParameter("password"))
                .build();
        if (validatingData(user)) {
            userService.save(user);
            request.getSession().setAttribute("id", user.getId());
        }
        else {
            ModelAndView modelAndView = new ModelAndView("signUpPage");
            modelAndView.addObject("message", "Please check entered values!");
            return new ModelAndView("signUpPage");
        }
        return new ModelAndView("redirect:/login");
    }

    private boolean validatingData(User user) {
        return !user.getName().equals("") && !user.getSurname().equals("") && !user.getPassword().equals("");
    }
}
