package com.turni.lifepayapp.mapper;

import com.turni.lifepayapp.bean.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BillMapper {
    @Select("select * from bill where u_id=#{uid}")
    List<Bill> getAll(Integer uid);
    @Select("select * from bill where id=#{id}")
    Bill getBillById(Integer id);
    @Select("select * from bill where YEAR(date)=#{year} and MONTH(date)=#{month} and u_id=#{uid}")
    List<Bill> getByYM(Integer year, Integer month, Integer uid);
    @Select("select * from bill where YEAR(date)=#{year} and u_id=#{uid}")
    List<Bill> getByY(Integer year, Integer uid);
}
