package com.turni.lifepayapp.controller;

import com.turni.lifepayapp.bean.Service;
import com.turni.lifepayapp.service.ServService;
import com.turni.lifepayapp.utils.HTTPrespose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/serv")
public class ServController {
    @Autowired
    private ServService servService;

    //tested!
    @GetMapping("/{cid}")
    public Map<String,Object> getServByCid(@PathVariable("cid") Integer cid) {
        System.out.printf("请求来自%d号城市的服务\n", cid);
        try {
            List<Service> list = servService.getServByCid(cid);
            Map<String, Object> map = HTTPrespose.successMessage("请求城市服务数据成功");
            map.put("serv", list);
            System.out.printf("请求来自%d号城市的服务成功\n", cid);
            return map;
        }catch (Exception e) {
            System.out.printf("请求来自%d号城市的服务失败\n", cid);
            return HTTPrespose.errorMessage(500, "服务器请求城市服务数据失败");
        }
    }



}
