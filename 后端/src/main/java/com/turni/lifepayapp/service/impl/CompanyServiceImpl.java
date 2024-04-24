package com.turni.lifepayapp.service.impl;

import com.turni.lifepayapp.bean.Company;
import com.turni.lifepayapp.mapper.CompanyMapper;
import com.turni.lifepayapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public List<Company> getCompanyByCSid(Integer s_id, Integer c_id) {
        try {
            return companyMapper.getCompanyByCSid(s_id, c_id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Company getCompanyById(Integer id) {
        try {
            return companyMapper.getCompanyById(id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
