package com.turni.lifepayapp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private Integer id;
    private Integer u_id;
    private String name;
    private Timestamp date;
    private Integer money;
}
