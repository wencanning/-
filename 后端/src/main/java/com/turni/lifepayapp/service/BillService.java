package com.turni.lifepayapp.service;

import com.turni.lifepayapp.bean.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAll();
    Bill getBillById(Integer id);
}
