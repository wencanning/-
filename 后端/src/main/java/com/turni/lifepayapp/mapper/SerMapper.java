package com.turni.lifepayapp.mapper;
import com.turni.lifepayapp.bean.Service;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SerMapper {
    @Select("select * from city where id = #{id}")
    Service getById(@Param("id") Integer id);
}
