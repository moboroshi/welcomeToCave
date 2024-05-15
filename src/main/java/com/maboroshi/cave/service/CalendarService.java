package com.maboroshi.cave.service;

import com.maboroshi.cave.pojo.CalendarInform;

import java.time.LocalDate;

public interface CalendarService {
    CalendarInform getInform(LocalDate date, Integer userId);

    void writeInform(CalendarInform calendarInform);

    void updateInform(CalendarInform calendarInform);
}
