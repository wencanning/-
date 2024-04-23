package com.turni.lifepayapp.controller;

import com.turni.lifepayapp.bean.Company;
import com.turni.lifepayapp.service.CompanyService;
import com.turni.lifepayapp.utils.HTTPrespose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("")
    Map<String, Object> getCompany(Integer s_id, Integer c_id) {
        if(s_id == null || c_id == null) {
            return HTTPrespose.errorMessage(403, "参数无效");
        }
        List<Company> list = companyService.getCompany(s_id, c_id);
        if(list == null) {
            return HTTPrespose.errorMessage(500, "服务器请求城市服务失败");
        }
        Map<String, Object> map = HTTPrespose.successMessage("请求成功");
        map.put("company", list);
        return map;
    }
}
