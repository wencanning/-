package com.turni.lifepayapp.mapper;

import com.turni.lifepayapp.bean.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BillMapper {
    @Select("select * from bill")
    List<Bill> getAll();
    @Select("select * from bill where id=#{id}")
    Bill getBillById(Integer id);
}
