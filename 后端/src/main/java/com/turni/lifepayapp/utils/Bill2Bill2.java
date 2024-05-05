package com.turni.lifepayapp.utils;

import com.turni.lifepayapp.bean.Bill;
import com.turni.lifepayapp.bean.Bill2;
import com.turni.lifepayapp.bean.Company;
import com.turni.lifepayapp.service.BillService;
import com.turni.lifepayapp.service.CompanyService;
import com.turni.lifepayapp.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Bill2Bill2 {

    @Autowired
    private static CompanyService companyService;

    @PostConstruct
    public void init() {
        Bill2Bill2.companyService = companyService;
    }
    public static Bill2 converse(Bill bill,CompanyService companyService ) {
        Timestamp timestamp = bill.getDate();
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        Integer year = localDateTime.getYear();
        Integer month = localDateTime.getMonthValue();
        Integer day = localDateTime.getDayOfMonth();
        Integer hour = localDateTime.getHour();
        Integer minute = localDateTime.getMinute();

        Company company = companyService.getCompanyById(bill.getComp_id());

        Bill2 res = new Bill2(bill.getId(), year, month, day, hour, minute, bill.getMoney(), company.getName());
        return res;
    }
    public static List<Bill2> converse(List<Bill> list,CompanyService companyService ) {
        List<Bill2> res = new LinkedList<>();
        for(int i = 0; i < list.size(); i++) {
            Bill2 bill2 = Bill2Bill2.converse(list.get(i), companyService);
            res.add(bill2);
        }
        return res;
    }
}
