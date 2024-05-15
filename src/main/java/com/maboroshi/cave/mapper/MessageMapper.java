package com.maboroshi.cave.mapper;

import com.maboroshi.cave.pojo.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Delete("delete from message where messageId = #{messageId}")
    void dalete(Integer messageId);

    @Update("update message set userId = #{userId}, count = #{count}, date = #{date}, text = #{text}, commentTime = #{commentTime} where messageId = #{messageId}")
    void update(Message message);

    @Insert("insert into message(userId, count, date, text, commentTime) VALUE (#{userId}, #{count}, #{date}, #{text}, #{commentTime} )")
    void add(Message message);

    @Select("select * from message ORDER BY messageId DESC")
    List<Message> list();
}
