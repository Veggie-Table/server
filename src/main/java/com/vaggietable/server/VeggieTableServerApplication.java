package com.vaggietable.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan
@SpringBootApplication
public class VeggieTableServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeggieTableServerApplication.class, args);
    }

}
