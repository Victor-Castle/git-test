package com.itheima;

import com.example.EnableHeaderConfig;
import com.example.HeaderConfig;
import com.example.MyImportSelector;
import com.example.TokenParser;
import com.itheima.utils.AliyunOSSOperator;
import com.itheima.utils.AliyunOSSProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//自动配置实现方案一：@Component + @ComponentScan
//@ComponentScan(basePackages = {"com.itheima", "com.example"})


//自动配置实现方案二：@Import
//@Import(TokenParser.class)//普通类，需要手动添加@Component注解才能被扫描到
//@Import(HeaderConfig.class)//配置类
//@Import(MyImportSelector.class)//ImportSelector实现类 - 批量导入
@EnableHeaderConfig
@SpringBootApplication//具备组件扫描功能，但是默认扫描的是启动类所在包及其子包
public class SpringbootWebConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebConfigApplication.class, args);
    }


}
