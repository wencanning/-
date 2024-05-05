package com.turni.lifepayapp.service;

import com.turni.lifepayapp.bean.Bill;

import java.sql.Timestamp;
import java.util.List;

public interface BillService {
    List<Bill> getAll(Integer uid);
    Bill getBillById(Integer id);
    List<Bill> getByYM(Integer year, Integer month, Integer uid);
    List<Bill> getByY(Integer year, Integer uid);
    void insert(Integer uid, Integer compid, Timestamp date, Double money);
}
