package com.victor.service;

import com.victor.pojo.PageResult;
import com.victor.pojo.Student;
import com.victor.pojo.StudentQueryParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    PageResult<Student> getList(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> ids);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void updateViolation(Integer id, short score);
}
