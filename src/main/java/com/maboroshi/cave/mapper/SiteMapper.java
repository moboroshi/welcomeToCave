package com.maboroshi.cave.mapper;

import com.maboroshi.cave.pojo.Site;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SiteMapper {

    @Select("select * from site where belongTo = #{belongTo}")
    List<Site> list( String belongTo);
}
