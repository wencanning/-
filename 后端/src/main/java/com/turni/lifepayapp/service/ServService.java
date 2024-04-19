package com.turni.lifepayapp.service;

import com.turni.lifepayapp.bean.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServService {
    List<Service> getServByCid(Integer cid);
}
