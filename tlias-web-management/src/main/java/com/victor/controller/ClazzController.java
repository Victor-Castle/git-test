package com.victor.controller;

import com.victor.anno.Log;
import com.victor.pojo.*;
import com.victor.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result getClazzList(ClazzQueryParam clazzQueryParam){
        log.info("查询班级列表:{}", clazzQueryParam);
        // 模拟查询班级列表
        PageResult<Clazz> clazzList = clazzService.getClazzList(clazzQueryParam);
        return Result.success(clazzList);
    }


    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id){
        log.info("删除班级:{}", id);
        clazzService.deleteClazz(id);
        return Result.success();
    }


    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){
        log.info("添加班级:{}", clazz);
        clazzService.addClazz(clazz);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result getClazz(@PathVariable Integer id){
        log.info("查询班级:{}", id);
        Clazz clazz = clazzService.getClazz(id);
        return Result.success(clazz);
    }


    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){
        log.info("更新班级:{}", clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getList(){
        log.info("查询班级列表");
        List<Clazz> clazzList = clazzService.getList();
        return Result.success(clazzList);
    }
}
