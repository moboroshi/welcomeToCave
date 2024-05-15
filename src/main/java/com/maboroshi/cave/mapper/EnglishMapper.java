package com.maboroshi.cave.mapper;

import com.maboroshi.cave.pojo.EnglishWord;
import com.maboroshi.cave.pojo.Userdata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EnglishMapper {
    @Select("select * from englishuserdata where userId = #{userId}")
    List<Userdata> listData(Integer userId);

    @Select("select * from english order by rand() limit 200")
    List<EnglishWord> list();

    @Update("INSERT INTO englishuserdata (userId, wordId,viewed,click) VALUES (#{userId}, #{wordId},#{viewed},#{click}) ON DUPLICATE KEY UPDATE viewed = #{viewed},click = #{click};")
    void update(Userdata userdata);

    @Select("select sum(viewed) from englishuserdata where userId = #{userId}")
    Integer getSum(Integer userId);
}
