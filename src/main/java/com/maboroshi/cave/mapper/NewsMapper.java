package com.maboroshi.cave.mapper;

import com.maboroshi.cave.pojo.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewsMapper {
    @Select("select * from bbcnews ORDER BY date desc LIMIT 7")
    List<News> listBBC();

    @Insert("INSERT INTO bbcnews(date, title, image, link) values (#{date},#{title},#{image},#{link})")
    void insertBBC(News news);

    @Insert("insert into gamenews(title, image, link) values (#{title},#{image},#{link})")
    void insertBHMT();

    @Select("select * from gamenews order by rand() limit 7")
    List<News> listBHMT();
}
