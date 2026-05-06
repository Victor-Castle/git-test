package com.victor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan//开启了springboot对Servlet组件的支持
@SpringBootApplication
public class SpringbootMybatisQuickstart03Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisQuickstart03Application.class, args);
    }

}