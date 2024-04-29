package com.turni.lifepayapp.service.impl;

import com.turni.lifepayapp.bean.User;
import com.turni.lifepayapp.mapper.UserMapper;
import com.turni.lifepayapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean insertWithoutId(String phon, String username, String img_url) {
        try {
            userMapper.insertWithoutId(phon,username,img_url);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByPhone(String phone) {
        try {
            return userMapper.getUserByPhone(phone);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserById(Integer id) {
        try {
            return userMapper.getUserById(id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        try {
            return userMapper.getAll();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getPhoneByUsername(String username) {
        try {
            return userMapper.getPhoneByUsername(username);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
