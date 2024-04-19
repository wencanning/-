package com.turni.lifepayapp.controller;

import com.turni.lifepayapp.bean.User;
import com.turni.lifepayapp.service.UserService;
import com.turni.lifepayapp.utils.HTTPrespose;
import com.turni.lifepayapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/login")
public class LoginController {
    @Autowired
    private UserService userService;

    private String img_url = "/static/icon/头像_男孩.png";
    @PostMapping("")
    public Map<String, Object> login(String phon, String messg) {
        System.out.println(phon);
        System.out.println(messg);
        try {
            User user = userService.getUserByPhone(phon);
            if(user == null) {
                userService.insertWithoutId(phon, messg, img_url);
            }
            if(!messg.equals("123456")) {
               return  HTTPrespose.errorMessage(403, "验证码错误");
            }
            String token = JwtUtil.sign(phon, messg);
            if(token == null) {
                return HTTPrespose.errorMessage(500, "服务器生成token错误");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("statusCode", "200");
            map.put("message", "登录成功");
            map.put("token", token);
            return map;
        }catch (Exception e) {
            e.printStackTrace();
            return HTTPrespose.errorMessage(500, "服务器发生未知错误");
        }
    }


}
