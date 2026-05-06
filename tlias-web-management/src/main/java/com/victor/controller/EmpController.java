package com.victor.controller;

import com.victor.mapper.EmpExprMapper;
import com.victor.mapper.EmpMapper;
import com.victor.pojo.Emp;
import com.victor.pojo.EmpQueryParam;
import com.victor.pojo.PageResult;
import com.victor.pojo.Result;
import com.victor.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;


//    @GetMapping
    //@RequestParam 设置默认值
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        log.info("分页查询：{},{},{},{},{}", page, pageSize, name, gender, begin, end);
//        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//
//
//    }

    @GetMapping
    public Result page(EmpQueryParam queryParam){
        log.info("分页查询：{}", queryParam);
        PageResult<Emp> pageResult = empService.page(queryParam);
        return Result.success(pageResult);
    }


    /*
     * 新增员工
     */

    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception{
        log.info("新增员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /*
    删除员工 - 数组
     */
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("删除员工:{}", Arrays.toString(ids));
//        return Result.success();
//    }

    /*
    删除员工 - List
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工:{}",ids.toString());
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 查询回显
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工的详细信息");
        Emp emp  = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 修改员工
     */
    /**
     * 更新员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }
}
