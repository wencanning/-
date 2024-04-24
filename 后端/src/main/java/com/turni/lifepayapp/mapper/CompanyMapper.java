package com.turni.lifepayapp.mapper;

import com.turni.lifepayapp.bean.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompanyMapper {
    @Select("select * from company where s_id=#{s_id} and c_id=#{c_id}")
    List<Company> getCompanyByCSid(@Param("s_id")Integer s_id, @Param("c_id")Integer c_id);
    @Select("select * from company where id=#{id}")
    Company getCompanyById(@Param("id") Integer id);
}
