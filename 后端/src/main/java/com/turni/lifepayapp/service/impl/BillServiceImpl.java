package com.turni.lifepayapp.service.impl;

import com.turni.lifepayapp.bean.Bill;
import com.turni.lifepayapp.mapper.BillMapper;
import com.turni.lifepayapp.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public List<Bill> getAll() {
        try {
            return billMapper.getAll();
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
}
