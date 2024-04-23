package com.turni.lifepayapp.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turni.lifepayapp.service.UserService;
import com.turni.lifepayapp.service.impl.UserServiceImpl;
import com.turni.lifepayapp.utils.HTTPrespose;
import com.turni.lifepayapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    public UserService userService;
    public static JwtInterceptor jwtInterceptor;
    @PostConstruct
    public void init() {
        jwtInterceptor = this;
        jwtInterceptor.userService = this.userService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token.equals("[object Undefined]")) {
            sendErrorMessage(response, "token未正确定义");
            return false;
        }
        boolean flag = true;
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        if(token != null) {
            String username = JwtUtil.getUserPhoneByToken(request);
            if(username == null) {
                System.out.println("interceptor: 无效请求");
                sendErrorMessage(response, "查找用户名失败");
                return false;
            }else {
                System.out.printf("interceptor: 用户%s发起请求\n", username);
            }
            String phone = jwtInterceptor.userService.getPhoneByUsername(username);
            if(phone == null) {
                System.out.printf("interceptor: 查找user=%s的手机号失败!\n", username);
                sendErrorMessage(response, "查询手机号失败");
                return false;
            }else {
                System.out.printf("interceptor: 该用户的手机号为%s\n", phone);
            }
            boolean result = JwtUtil.verify(token, username, phone);
            if(result) {
                System.out.println("interceptor: 请求通过");
                return true;
            }
        }
        sendErrorMessage(response, "无访问权限");
        return false;
    }

    private void sendErrorMessage(HttpServletResponse response, String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> errorData = new HashMap<>();

        errorData.put("message", message);
        errorData.put("statusCode", 401);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {

            response.getWriter().write(objectMapper.writeValueAsString(errorData));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
