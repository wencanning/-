package com.turni.lifepayapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.turni.lifepayapp.mapper")
public class LifePayAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifePayAppApplication.class, args);
    }

}
