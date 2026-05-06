package com.victor.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.victor.mapper.EmpExprMapper;
import com.victor.mapper.EmpMapper;
import com.victor.pojo.*;
import com.victor.service.EmpLogService;
import com.victor.service.EmpService;
import com.victor.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {


    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;


    // -----------------原始分页查询实现------------------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.调用mapper接口，查询总记录数
//        Long total = empMapper.count();
//        //2.调用mapper接口，查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        //3.封装结果
//        return new PageResult<Emp>(total, rows);
//
//    }


    /*
     * PageHelper分页查询
     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //1.设置分页参数
//        PageHelper.startPage(page, pageSize);
//        //2.执行查询
//        List<Emp> rows = empMapper.list(name, gender, begin, end);
//        Page<Emp> p = (Page<Emp>) rows;
//        //3.解析查询结果，并封装
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam queryParam) {
        //1.设置分页参数
        PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        //2.执行查询
        List<Emp> rows = empMapper.list(queryParam);
        Page<Emp> p = (Page<Emp>) rows;
        //3.解析查询结果，并封装
        return new PageResult<Emp>(p.getTotal(), p.getResult());

    }

    @Transactional(rollbackFor = {Exception.class})//事务管理 - 默认出现运行时异常RuntimeException才会回滚事务
    @Override
    public void save(Emp emp) throws Exception {
        try {
            //1.保存员工的基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        //int i =1/0;
//        if(true){
//            throw new Exception("测试异常");
//        }

        //2.保存员工的工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合为empId赋值
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
            }finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }

    }

    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        //1. 根据ID批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //2. 根据员工的ID批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2. 根据员工ID删除员工的工作经历信息 【删除老的】
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //3. 新增员工的工作经历数据 【新增新的】
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper接口，根据用户名和密码查询员工基本信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        //2.判读：判断是否存在这个员工，如果存在，组装登录成功信息
        if(e != null){
            log.info("登录成功，员工信息：{}", e);
            //生成JWT令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("Id", e.getId());
            claims.put("Name", e.getName());
            claims.put("Username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return new LoginInfo(e.getId(), e.getName(), e.getUsername(),jwt);
        }

        //3不存在，返回null
        return null;
    }

}
