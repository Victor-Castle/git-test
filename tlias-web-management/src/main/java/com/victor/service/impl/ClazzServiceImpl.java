package com.victor.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.victor.mapper.ClazzMapper;
import com.victor.pojo.Clazz;
import com.victor.pojo.ClazzQueryParam;
import com.victor.pojo.EmpQueryParam;
import com.victor.pojo.PageResult;
import com.victor.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> getClazzList(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2.执行查询
        List<Clazz> rows = clazzMapper.list(clazzQueryParam);
        Page<Clazz> p = (Page<Clazz>) rows;
        //3.解析查询结果，并封装
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteClazz(Integer id) {
        clazzMapper.delete(id);
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getClazz(Integer id) {
        return clazzMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> getList() {

        return clazzMapper.getList();
    }
}
