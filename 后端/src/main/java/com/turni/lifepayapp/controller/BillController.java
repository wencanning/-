package com.turni.lifepayapp.controller;

import com.turni.lifepayapp.bean.Bill;
import com.turni.lifepayapp.bean.Bill2;
import com.turni.lifepayapp.service.BillService;
import com.turni.lifepayapp.utils.Bill2Bill2;
import com.turni.lifepayapp.utils.HTTPrespose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @GetMapping("")
    Map<String, Object> getAll() {
        List<Bill> list = billService.getAll();
        if(list == null)
            return HTTPrespose.errorMessage(500, "服务器请求所有账单错误");
        System.out.println(list);
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        List<Bill2> list2 = Bill2Bill2.converse(list);
        map.put("bill", list2);
        return map;
    }
    @GetMapping("/id")
    Map<String, Object> getBillById(Integer id) {
        Bill bill = billService.getBillById(id);
        if(bill == null)
            return HTTPrespose.errorMessage(500, "服务器请求账单错误");
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        Bill2 bill2 = Bill2Bill2.converse(bill);
        map.put("bill", bill2);
        return map;
    }
}
