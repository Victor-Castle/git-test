package com.victor.controller;

import com.victor.pojo.PageResult;
import com.victor.pojo.Result;
import com.victor.pojo.Student;
import com.victor.pojo.StudentQueryParam;
import com.victor.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {


    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result getList(StudentQueryParam studentQueryParam) {
        log.info("查询学生列表: {}", studentQueryParam);
        PageResult<Student> pageResult = studentService.getList(studentQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据ID删除学生: {}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加学生: {}", student);
        studentService.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("根据ID查询学生: {}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("更新学生: {}", student);
        studentService.update(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable short score) {
        log.info("更新学生违规记录: {}, {}", id, score);
        studentService.updateViolation(id, score);
        return Result.success();
    }
}