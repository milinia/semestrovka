package ru.itis.services.old;

import java.util.Date;
import java.util.List;

public interface DateEmailService {

    void create(Date date, String email);
    void delete(Date date, String email);
    List<String> getEmailsByDate(Date date);
}
