package com.turni.lifepayapp.service.impl;

import com.turni.lifepayapp.bean.City;
import com.turni.lifepayapp.mapper.CityMapper;
import com.turni.lifepayapp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;


    @Override
    public City getCityById(Integer id) {
        if(cityMapper == null) {
            System.out.println("is null");
        }
        return cityMapper.getById(id);
    }
}
