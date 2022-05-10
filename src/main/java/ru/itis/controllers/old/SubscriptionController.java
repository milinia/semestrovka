package ru.itis.controllers.old;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.models.old.Subscription;
import ru.itis.services.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(method = RequestMethod.POST, value = "/delete_sub")//общий для subscription
    public String deleteSub(HttpServletRequest request, @RequestParam Long numberOfSub){
        Long id = Long.parseLong(request.getSession().getAttribute("id").toString());
        subscriptionService.deleteById(numberOfSub, id);
        return "redirect:/profile";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add_sub")
    public String addSubscription(HttpServletRequest request){
        Long id = Long.parseLong(request.getSession().getAttribute("id").toString());
        Subscription subscription = Subscription.builder()
                .serviceName(request.getParameter("service"))
                .endingDate(new Date(parseToSqlDate(request.getParameter("date"))))
                .userId(id)
                .build();
        subscriptionService.createNew(subscription);
        return "redirect:/profile";
    }

    private long parseToSqlDate(String date){
        String[] values = date.split("[-]");
        int day = Integer.parseInt(values[2]);
        int month = Integer.parseInt(values[1]);
        int year = Integer.parseInt(values[0]);
        LocalDate start = new LocalDate(1970, 1, 1);
        LocalDate end = new LocalDate(year, month, day);
        return Days.daysBetween(start, end).getDays() * 86400000L;
    }
}
