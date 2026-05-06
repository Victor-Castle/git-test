package com.victor.service;


import com.victor.pojo.Clazz;
import com.victor.pojo.ClazzQueryParam;
import com.victor.pojo.EmpQueryParam;
import com.victor.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClazzService {
    PageResult<Clazz> getClazzList(ClazzQueryParam clazzQueryParam);

    void deleteClazz(Integer id);

    void addClazz(Clazz clazz);

    Clazz getClazz(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> getList();
}
