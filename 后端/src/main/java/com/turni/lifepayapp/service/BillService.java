package com.turni.lifepayapp.service;

import com.turni.lifepayapp.bean.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAll(Integer uid);
    Bill getBillById(Integer id);
    List<Bill> getByYM(Integer year, Integer month, Integer uid);
}
