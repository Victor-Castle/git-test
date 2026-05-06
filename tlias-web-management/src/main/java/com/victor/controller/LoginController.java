package com.victor.controller;

import com.victor.pojo.Emp;
import com.victor.pojo.LoginInfo;
import com.victor.pojo.Result;
import com.victor.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录Controller
 */

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 登录
     */
    @PostMapping
    public Result login(@RequestBody Emp emp) {
        log.info("登录:{}", emp);
        //登录成功
        LoginInfo info = empService.login(emp);
        if (info == null) {
            return Result.error("用户名或密码错误");
        }
        return Result.success(info);
    }
}
