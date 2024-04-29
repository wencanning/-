package com.turni.lifepayapp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillItem {
    private String year_month;
    private List<Bill2> list;
}
