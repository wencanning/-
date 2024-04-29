package com.turni.lifepayapp.utils;

import com.turni.lifepayapp.bean.Bill;
import com.turni.lifepayapp.bean.Bill2;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Bill2Bill2 {
    public static Bill2 converse(Bill bill) {
        Timestamp timestamp = bill.getDate();
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        Integer year = localDateTime.getYear();
        Integer month = localDateTime.getMonthValue();
        Integer day = localDateTime.getDayOfMonth();
        Integer hour = localDateTime.getHour();
        Integer minute = localDateTime.getMinute();

        Bill2 res = new Bill2(bill.getId(), year, month, day, hour, minute, bill.getMoney(), bill.getName());
        return res;
    }
    public static List<Bill2> converse(List<Bill> list) {
        List<Bill2> res = new LinkedList<>();
        for(int i = 0; i < list.size(); i++) {
            Bill2 bill2 = Bill2Bill2.converse(list.get(i));
            res.add(bill2);
        }
        return res;
    }
}
