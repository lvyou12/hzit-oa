package com.hzitoa;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 吕游
 * @company 合众艾特
 * @create 2017-09-22 10:05
 * @description
 */
@ComponentScan(value = {"com.hzitoa.web","com.hzitoa.service","com.hzitoa.mapper"})
@MapperScan("com.hzitoa.mapper*")
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
