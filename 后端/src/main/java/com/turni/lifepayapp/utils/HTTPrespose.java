package com.turni.lifepayapp.utils;

import java.util.HashMap;
import java.util.Map;

public class HTTPrespose {
    public static Map<String, Object> errorMessage(Integer statusCode, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", statusCode);
        map.put("message", message);
        return map;
    }

    public static Map<String, Object> successMessage(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 200);
        map.put("message", message);
        return map;
    }
}
