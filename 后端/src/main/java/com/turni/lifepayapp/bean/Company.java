package com.turni.lifepayapp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private Integer id;
    private String name;
    private Integer s_id;
    private Integer c_id;
}
