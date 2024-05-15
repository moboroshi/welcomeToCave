package com.maboroshi.cave.mapper;

import com.maboroshi.cave.pojo.Userdata;
import com.maboroshi.cave.pojo.JapaneseWord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface JapaneseMapper {
    @Select("select * from japaneseuserdata where userId = #{userId}")
    List<Userdata> listData(Integer userId);

    @Select("select * from japanese order by rand() limit 200")
    List<JapaneseWord> list();

    @Update("INSERT INTO japaneseuserdata (userId, wordId,viewed,click) VALUES (#{userId}, #{wordId},#{viewed},#{click}) ON DUPLICATE KEY UPDATE viewed = #{viewed},click = #{click};")
    void update(Userdata userdata);

    @Select("select sum(viewed) from japaneseuserdata where userId = #{userId}")
    Integer getSum(Integer userId);
}
