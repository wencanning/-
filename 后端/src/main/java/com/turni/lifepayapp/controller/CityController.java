package com.turni.lifepayapp.controller;

import com.turni.lifepayapp.bean.City;
import com.turni.lifepayapp.service.CityService;
import com.turni.lifepayapp.service.impl.CityServiceImpl;
import com.turni.lifepayapp.utils.HTTPrespose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("")
    public Map<String, Object> getCity() {
        List<City> list = cityService.getCity();
        if(list == null) {
            return HTTPrespose.errorMessage(500, "获取城市信息失败");
        }
        Map<String, Object> map = HTTPrespose.successMessage("获取城市信息成功");
        map.put("citylist", list);
        return map;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCity(@PathVariable("id") Integer id ) {
        City city = cityService.getCityById(id);
        if(city == null) {
            return HTTPrespose.errorMessage(500, "获取城市信息失败");
        }
        Map<String, Object> map = HTTPrespose.successMessage("获取城市信息成功");
        map.put("city", city);
        return map;
    }
}
