package com.atguigu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.atguigu.auth.mapper")
public class ServiceAuthApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServiceAuthApplication.class, args);

    }
}