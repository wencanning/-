package com.turni.lifepayapp.mapper;


import com.turni.lifepayapp.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(phonenumber, username, img_url) values(#{phone},#{name}, #{url})")
    void insertWithoutId(@Param("phone")String phon, @Param("name") String username, @Param("url")String img_url);
    @Select("select * from user where phonenumber=#{phone}")
    User getUserByPhone(@Param("phone")String phone);
}
