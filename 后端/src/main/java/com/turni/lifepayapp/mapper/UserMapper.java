package com.turni.lifepayapp.mapper;


import com.turni.lifepayapp.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> getAll();
    @Insert("insert into user(phonenumber, username, img_url) values(#{phone},#{name}, #{url})")
    void insertWithoutId(@Param("phone")String phon, @Param("name") String username, @Param("url")String img_url);
    @Select("select * from user where phonenumber=#{phone}")
    User getUserByPhone(@Param("phone")String phone);
    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id")Integer id);
    @Select("select phonenumber from user where username=#{username}")
    String getPhoneByUsername(@Param("username") String username);
}
