package com.turni.lifepayapp.service;

import com.turni.lifepayapp.bean.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getCompany(Integer s_id, Integer c_id);
}
