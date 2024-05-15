package com.maboroshi.cave.controller;


import com.maboroshi.cave.anno.Log;
import com.maboroshi.cave.pojo.CalendarInform;
import com.maboroshi.cave.pojo.Result;
import com.maboroshi.cave.service.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @Log
    @GetMapping("/{date}/{userId}")
    public Result getInform(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                            @PathVariable("userId") Integer userId){
        return Result.success(calendarService.getInform(date,userId));
    }
    @Log
    @PostMapping("")
    public Result writeInform(@RequestBody CalendarInform calendarInform){
        calendarService.writeInform(calendarInform);
        return Result.success();
    }
    @Log
    @PutMapping("")
    public Result updateInform(@RequestBody CalendarInform calendarInform){
        calendarService.updateInform(calendarInform);
        return Result.success();
    }
}
