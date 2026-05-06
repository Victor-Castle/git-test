package com.itheima;

import cn.hutool.core.io.FileUtil;
import com.example.HeaderGenerator;
import com.example.HeaderParser;
import com.example.TokenParser;
import com.itheima.utils.AliyunOSSOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.File;

@SpringBootTest
class SpringbootWebTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @Test
    public void testScope(){
        for (int i = 0; i < 1000; i++) {
            Object deptController = applicationContext.getBean("deptController");
            System.out.println(deptController);
        }

    }

    @Test
    public void testUpload() throws Exception {
        File file = new File("C:\\Users\\32737\\Pictures\\th.jpg");
        String url = aliyunOSSOperator.upload(FileUtil.readBytes(file),"th.jpg");
        System.out.println(url);
    }

//    @Autowired
//    private TokenParser tokenParser;
//
//    @Test
//    public void testTokenParser() {
//        tokenParser.parse();
//    }


    @Autowired
    private HeaderParser headerParser;

    @Autowired
    private HeaderGenerator headerGenerator;
    @Test
    public void testHeaderConfig() {
        headerParser.parse();
        headerGenerator.generate();
    }
}
