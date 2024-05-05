package com.turni.lifepayapp.controller;

import com.turni.lifepayapp.bean.*;
import com.turni.lifepayapp.service.BillService;
import com.turni.lifepayapp.service.CompanyService;
import com.turni.lifepayapp.service.PdfService;
import com.turni.lifepayapp.service.UserService;
import com.turni.lifepayapp.utils.Bill2Bill2;
import com.turni.lifepayapp.utils.HTTPrespose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
    @Autowired
    private CompanyService companyService;
    @GetMapping("")
    Map<String, Object> getAll(Integer id) {
        List<Bill> list = billService.getAll(id);
        if(list == null)
            return HTTPrespose.errorMessage(500, "服务器请求所有账单错误");
        System.out.println(list);
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        List<Bill2> list2 = Bill2Bill2.converse(list, companyService);
        map.put("bill", list2);
        return map;
    }
    @GetMapping("/id")
    Map<String, Object> getBillById(Integer id) {
        Bill bill = billService.getBillById(id);
        if(bill == null)
            return HTTPrespose.errorMessage(500, "服务器请求账单错误");
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        Bill2 bill2 = Bill2Bill2.converse(bill, companyService);
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
        List<Bill2> list2 = Bill2Bill2.converse(list, companyService);
        map.put("bill", list2);
        return map;
    }
    @GetMapping("/chart/month")
    Map<String, Object> getCateByMonth(String dateStr, Integer uid) {
        if(dateStr == null || uid == null)
            return HTTPrespose.errorMessage(403, "参数无效");
        Integer year = getYear(dateStr);
        Integer month = getMonth(dateStr);
        List<Double> list3 = new LinkedList<>();
        Double mmoney = 0.0;
        Integer billnum = 0;
        for(int i = 1; i <= 12; i++) {
            Double sum = 0.0;
            List<Bill> list1 = billService.getByYM(year, i, uid);
            List<Bill2> list2 = Bill2Bill2.converse(list1, companyService);
            for(Bill2 bill2 : list2) {
                sum += bill2.getMoney();
            }
            if(i == month) {
                mmoney = sum;
                billnum = list2.size();
            }
            list3.add(sum);
        }
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        map.put("moneylist", list3);
        map.put("billnum", billnum);
        map.put("mmoney", mmoney);
        return map;
    }
    @GetMapping("/chart/year")
    Map<String, Object> getCateByYear(Integer dateStr, Integer uid) {
        if(dateStr == null || uid == null)
            return HTTPrespose.errorMessage(403, "参数无效");
        Integer year = dateStr;
        Integer billnum = 0;
        Double mmoney = 0.0;
        List<Double> list = new LinkedList<>();
        for(int i = year-4; i <= year+7; i++) {
            List<Bill> bills = billService.getByY(i, uid);
            Double sum = 0.0;
            for(Bill bill : bills) {
                sum += bill.getMoney();
            }
            if(i == year) {
                billnum = bills.size();
                mmoney = sum;
            }
            list.add(sum);
        }
        Map<String,Object> map = HTTPrespose.successMessage("请求成功");
        map.put("billnum", billnum);
        map.put("mmoney", mmoney);
        map.put("moneylist", list);
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
    @GetMapping("/rank10")
    public Map<String, Object> getRank10(String dateStr, Integer uid) {
        Integer year = getYear(dateStr);
        Integer month = getMonth(dateStr);
        List<Bill> list1 = billService.getByYM(year, month, uid);
        Collections.sort(list1, new Comparator<Bill>() {
            @Override
            public int compare(Bill bill1, Bill bill2) {
                return bill2.getMoney().compareTo(bill1.getMoney()); // 降序排序
            }
        });
        int end = Math.min(10, list1.size());
        List<Bill2> list2 = Bill2Bill2.converse(list1, companyService);
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        map.put("ranklist", list2.subList(0, end));
        return map;
    }
    @GetMapping("/check")
    public Map<String, Object> checkUserNum(String usernum) {
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        if(usernum.equals("202013407479")) {
            map.put("flag", true);
        }else {
            map.put("flag", false);
        }
        return map;
    }

    @GetMapping("/getpay")
    public Map<String, Object> getPayDetail(Integer uid, Integer comid) {
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        Pay pay = new Pay(getRandomDouble(), 0.0, "文灿宁");
        map.put("payItem", pay);
        return map;
    }

    @PostMapping("/pay")
    public Map<String, Object> payBill(@RequestBody PayRequest payRequest) {
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        billService.insert(payRequest.getUid(), payRequest.getCompid(), timestamp, payRequest.getPaynum());
        map.put("flag", true);
        return map;
    }

    private List<BillItem> billGroupByMonth(List<Bill> list) {
        List<Bill2> list2 = Bill2Bill2.converse(list, companyService);
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

    private Double getTotalMoneyByMonth(Integer year, Integer month, Integer uid) {
        List<Bill> list = billService.getByYM(year, month, uid);
        Double sum = 0.0;
        for(int i = 0; i < list.size(); i++) {
            sum += list.get(i).getMoney();
        }
        return sum;
    }

    private Integer getYear(String dateStr) {
        dateStr = dateStr + "-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        Integer year = date.getYear();
        return year;
    }

    private Integer getMonth(String dateStr) {
        dateStr = dateStr + "-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        Integer month = date.getMonthValue();
        return month;
    }

    private Double getRandomDouble() {
        Random random = new Random();
        Double randomDouble = random.nextDouble() * 200;
        return randomDouble;
    }
}
