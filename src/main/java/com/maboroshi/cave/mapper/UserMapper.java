package com.maboroshi.cave.mapper;

import com.maboroshi.cave.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select  * from user where username = #{username} and password = #{password}")
    User login(User user);

    @Insert("insert into user(username, nickname, password, userImg, createTime, updateTime) values (#{username}, #{nickname}, #{password}, #{userImg}, #{createTime}, #{updateTime})")
    void add(User user);

    @Update("update user set nickname = #{nickname}, userImg = #{userImg}, updateTime = #{updateTime} where userId = #{userId}")
    void update(User user);

    @Select("select * from user where userId = #{userId}")
    User getUser(Integer userId);
}
