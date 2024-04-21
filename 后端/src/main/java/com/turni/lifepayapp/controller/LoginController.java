package com.turni.lifepayapp.controller;

import com.turni.lifepayapp.bean.LoginRequest;
import com.turni.lifepayapp.bean.User;
import com.turni.lifepayapp.service.UserService;
import com.turni.lifepayapp.utils.HTTPrespose;
import com.turni.lifepayapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/login")
public class LoginController {
    @Autowired
    private UserService userService;

    private String img_url = "/static/icon/头像_男孩.png";

    private String alloCateUserName() {
        return  "用户_wx" + userService.getAll().size();
    }

    @PostMapping("")
    public Map<String, Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            String phone = loginRequest.getPhone();
            String message = loginRequest.getMessage();
            if(phone == null)
                return HTTPrespose.errorMessage(403, "登录中的号码为空");
            if(message == null)
                return HTTPrespose.errorMessage(403, "登录中的验证码为空");

            User user = userService.getUserByPhone(phone);
            if(!message.equals("123456")) {
                System.out.println(message);
               return  HTTPrespose.errorMessage(403, "验证码错误");
            }
            //为新用户创建账号
            if(user == null) {
                String username = alloCateUserName();
                if(username == null)
                        return HTTPrespose.errorMessage(500, "服务器为用户创建新用户失败");
                userService.insertWithoutId(phone,username,img_url);
                user = userService.getUserByPhone(phone);
            }
            String token = JwtUtil.sign(phone, message);
            if(token == null) {
                return HTTPrespose.errorMessage(500, "服务器生成token错误");
            }
            Map<String, Object> map = HTTPrespose.successMessage("登录成功");
            map.put("token", token);
            map.put("user", user);
            return map;
        }catch (Exception e) {
            e.printStackTrace();
            return HTTPrespose.errorMessage(500, "服务器发生未知错误");
        }
    }


}
