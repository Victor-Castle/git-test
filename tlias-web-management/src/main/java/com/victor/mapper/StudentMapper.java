package com.victor.mapper;

import com.victor.pojo.Student;
import com.victor.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> getList(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> ids);

    void insert(Student student);

    Student getById(Integer id);

    void update(Student student);

    void updateViolation(Integer id, short score);

    @MapKey("classList")
    List<Map<String, Object>> countStudentCountData();

    @MapKey("name")
    List<Map> countStudentDegreeData();
}
