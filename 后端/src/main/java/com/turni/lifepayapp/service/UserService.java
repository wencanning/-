package com.turni.lifepayapp.service;


import com.turni.lifepayapp.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    boolean insertWithoutId(String phon, String username, String img_url);
    User getUserByPhone(String phone);
    List<User> getAll();
    String getPhoneByUsername(String username);
}
