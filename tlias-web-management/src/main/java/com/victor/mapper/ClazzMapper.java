package com.victor.mapper;

import com.victor.pojo.Clazz;
import com.victor.pojo.ClazzQueryParam;
import com.victor.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    void delete(Integer id);

    void insert(Clazz clazz);

    Clazz selectByPrimaryKey(Integer id);

    void update(Clazz clazz);

    List<Clazz> getList();
}
