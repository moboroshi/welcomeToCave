package com.maboroshi.cave.mapper;

import com.maboroshi.cave.pojo.Reply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyMapper {
    @Select("select * from replies where messageId = #{messageId}")
    List<Reply> list(Integer messageId);
    @Delete("delete from replies where replyId = #{replyId}")
    void delete(Integer replyId);
    @Insert("insert into replies(userId, date, text, replyTime, messageId) VALUE (#{userId}, #{date}, #{text}, #{replyTime}, #{messageId})")
    void add(Reply reply);
}
