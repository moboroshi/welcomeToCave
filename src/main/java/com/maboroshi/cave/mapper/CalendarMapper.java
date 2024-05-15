package com.maboroshi.cave.mapper;

import com.maboroshi.cave.pojo.CalendarInform;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;

@Mapper
public interface CalendarMapper {

    @Select("select * from calendarinform where date = #{date} and userId = #{userId}")
    CalendarInform getInform(@Param("date") LocalDate date, @Param("userId") Integer userId);

    @Insert("insert into calendarinform(userId, date, text, updateTime) VALUE (#{userId}, #{date}, #{text}, #{updateTime})")
    void writeInform(CalendarInform calendarInform);

    void updateInform(CalendarInform calendarInform);
}
