package com.turni.lifepayapp.mapper;
import com.turni.lifepayapp.bean.Service;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ServMapper {
    // tested!
    @Select("select * from service where id in (select s_id from cs where c_id = #{id})")
    List<Service> getServByCid(@Param("id") Integer cid);
}
