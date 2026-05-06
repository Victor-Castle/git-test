package com.victor.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.victor.mapper.StudentMapper;
import com.victor.pojo.PageResult;
import com.victor.pojo.Student;
import com.victor.pojo.StudentQueryParam;
import com.victor.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> getList(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> rows = studentMapper.getList(studentQueryParam);
        Page<Student> p = (Page<Student>) rows;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        studentMapper.insert(student);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void updateViolation(Integer id, short score) {
        studentMapper.updateViolation(id, score);
    }
}
