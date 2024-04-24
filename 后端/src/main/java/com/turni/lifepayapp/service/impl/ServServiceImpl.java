package com.turni.lifepayapp.service.impl;

import com.turni.lifepayapp.mapper.ServMapper;
import com.turni.lifepayapp.service.ServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServServiceImpl implements ServService {
    @Autowired
    private ServMapper servMapper;
    @Override
    public List<com.turni.lifepayapp.bean.Service> getServByCid(Integer cid) {
        return servMapper.getServByCid(cid);
    }

    @Override
    public com.turni.lifepayapp.bean.Service getServById(Integer id) {
        try {
            return servMapper.getServById(id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
