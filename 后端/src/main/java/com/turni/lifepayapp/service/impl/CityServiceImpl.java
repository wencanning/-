package com.turni.lifepayapp.service.impl;

import com.turni.lifepayapp.bean.City;
import com.turni.lifepayapp.mapper.CityMapper;
import com.turni.lifepayapp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;

    @Override
    public City getCityById(Integer id) {
        try {
            return cityMapper.getById(id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<City> getCity() {
        try {
            return cityMapper.getCity();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
