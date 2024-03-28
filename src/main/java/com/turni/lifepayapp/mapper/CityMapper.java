package com.turni.lifepayapp.mapper;

import com.turni.lifepayapp.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface CityMapper {
    @Select("select * from city where id = #{id}")
    City getById(@Param("id") Integer id);
}
