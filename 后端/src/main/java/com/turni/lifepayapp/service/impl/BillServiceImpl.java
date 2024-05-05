package com.turni.lifepayapp.service.impl;

import com.turni.lifepayapp.bean.Bill;
import com.turni.lifepayapp.mapper.BillMapper;
import com.turni.lifepayapp.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public List<Bill> getAll(Integer uid) {
        try {
            return billMapper.getAll(uid);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Bill getBillById(Integer id) {
        try {
            return billMapper.getBillById(id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Bill> getByYM(Integer year, Integer month, Integer uid) {
        try {
            return billMapper.getByYM(year, month, uid);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Bill> getByY(Integer year, Integer uid) {
        try {
            return billMapper.getByY(year, uid);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(Integer uid, Integer compid, Timestamp date, Double money) {
        try {
            billMapper.insert(uid, compid, date, money);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
