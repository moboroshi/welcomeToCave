package com.maboroshi.cave.service.impl;

import com.maboroshi.cave.mapper.CalendarMapper;
import com.maboroshi.cave.pojo.CalendarInform;
import com.maboroshi.cave.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CalendarServiceimpl implements CalendarService {
    @Autowired
    private CalendarMapper calendarMapper;

    @Override
    public CalendarInform getInform(LocalDate date, Integer userID) {
        return calendarMapper.getInform(date,userID);
    }

    @Override
    public void writeInform(CalendarInform calendarInform) {
        calendarMapper.writeInform(calendarInform);
    }

    @Override
    public void updateInform(CalendarInform calendarInform) {
        calendarMapper.updateInform(calendarInform);
    }
}
