package com.turni.lifepayapp.controller;

import com.turni.lifepayapp.bean.City;
import com.turni.lifepayapp.service.CityService;
import com.turni.lifepayapp.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/")
    public String getCity() {
        return "for test.";
    }

    @GetMapping("/{id}")
    public City getUser(@PathVariable("id") Integer id ) {
        System.out.println(id);
        return cityService.getCityById(id);
    }
}
