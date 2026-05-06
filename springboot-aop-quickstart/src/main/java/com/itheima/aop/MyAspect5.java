package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class MyAspect5 {
    //前置通知
//    @Before("execution(* com.itheima.service.impl.DeptServiceImpl.list(..))||"
//            +"execution(* com.itheima.service.impl.DeptServiceImpl.save(..))"
//    )
    @Before("@annotation(com.itheima.anno.LogOperation)")
    public void before(){
        log.info("MyAspect5 -> before ...");
    }


}
