package com.turni.lifepayapp.controller;

import com.turni.lifepayapp.bean.Bill;
import com.turni.lifepayapp.bean.Bill2;
import com.turni.lifepayapp.bean.BillItem;
import com.turni.lifepayapp.bean.User;
import com.turni.lifepayapp.service.BillService;
import com.turni.lifepayapp.service.PdfService;
import com.turni.lifepayapp.service.UserService;
import com.turni.lifepayapp.utils.Bill2Bill2;
import com.turni.lifepayapp.utils.HTTPrespose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private UserService userService;
    @Autowired
    private PdfService pdfService;
    @GetMapping("")
    Map<String, Object> getAll(Integer id) {
        List<Bill> list = billService.getAll(id);
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
    @GetMapping("/ym")
    Map<String, Object> getBillByYM(String dateStr, Integer uid) {
        if(dateStr == null)
            return HTTPrespose.errorMessage(403, "参数无效");
        System.out.println(dateStr);
        dateStr = dateStr + "-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        int year = date.getYear();
        int month = date.getMonthValue();
        List<Bill> list = billService.getByYM(year, month, uid);
        if(list == null)
            return HTTPrespose.errorMessage(500, "服务器请求账单by date错误");
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        List<Bill2> list2 = Bill2Bill2.converse(list);
        map.put("bill", list2);
        return map;
    }
    @GetMapping(value = "/getpdf", produces = MediaType.APPLICATION_PDF_VALUE )
    public void generateReport(HttpServletResponse response ,@RequestParam("uid")  Integer uid) throws IOException {
        if(uid == 0) {
            System.out.println("uid is null.");
        }
        Map<String, Object> data = new HashMap<>();
        User user = userService.getUserById(uid);
        List<Bill> list = billService.getAll(uid);
        List<BillItem> itemlist = billGroupByMonth(list);
        data.put("billTitle", user.getUsername()+"的账单");
        data.put("list", itemlist);

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "bill" + ".pdf\"");
        pdfService.generatePdf(response.getOutputStream(), data);
    }

    private List<BillItem> billGroupByMonth(List<Bill> list) {
        List<Bill2> list2 = Bill2Bill2.converse(list);
        Map<String, List<Bill2>> map = list2.stream().collect
                (Collectors.groupingBy(bill->bill.getYear()+"-"+String.format("%02d",bill.getMonth())));
        List<BillItem> res = new LinkedList<>();
        for (Map.Entry<String, List<Bill2>> entry : map.entrySet()) {
            BillItem billItem = new BillItem();
            billItem.setYear_month(entry.getKey());
            billItem.setList(entry.getValue());
            res.add(billItem);
        }
        System.out.println(res);
        return res;
    }
}
