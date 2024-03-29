package com.turni.lifepayapp.controller;

import com.turni.lifepayapp.bean.Service;
import com.turni.lifepayapp.service.ServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/serv")
public class ServController {
    @Autowired
    private ServService servService;

    //tested!
    @GetMapping("/{cid}")
    public List<Service>getServByCid(@PathVariable("cid") Integer cid) {
        return servService.getServByCid(cid);
    }



}
