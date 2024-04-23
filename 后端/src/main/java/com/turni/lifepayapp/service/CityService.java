package com.turni.lifepayapp.service;

import com.turni.lifepayapp.bean.City;

import java.util.List;

public interface CityService {
     City getCityById(Integer id);
     List<City> getCity();
}
