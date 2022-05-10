package ru.itis.helper;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.Date;

public class DateAdapter {

    public long parseToSqlDate(String date){
        String[] values = date.split("[-]");
        int day = Integer.parseInt(values[2]);
        int month = Integer.parseInt(values[1]);
        int year = Integer.parseInt(values[0]);
        LocalDate start = new LocalDate(1970, 1, 1);
        LocalDate end = new LocalDate(year, month, day);
        return Days.daysBetween(start, end).getDays() * 86400000L;
    }

    public long parseToSqlDate(Date date){
        return date.getTime();
    }
}
