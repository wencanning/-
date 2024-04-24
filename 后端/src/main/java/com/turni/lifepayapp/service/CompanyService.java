package com.turni.lifepayapp.service;

import com.turni.lifepayapp.bean.Company;
import com.turni.lifepayapp.mapper.CompanyMapper;

import java.util.List;

public interface CompanyService {
    List<Company> getCompanyByCSid(Integer s_id, Integer c_id);
    Company getCompanyById(Integer id);
}
