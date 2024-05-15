package com.maboroshi.cave.mapper;

import com.maboroshi.cave.pojo.anime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface NewAnimeMapper {
    @Select("select * from newanime where season = #{season}")
    List<anime> list(Integer season);
    @Select("select * from newanime where season = #{season} and day = #{day}")
    List<anime> listByDay(@Param("season")Integer season, @Param("day") Integer day);
}
