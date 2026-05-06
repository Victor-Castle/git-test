package com.victor.service;

import com.victor.pojo.Emp;
import com.victor.pojo.EmpQueryParam;
import com.victor.pojo.LoginInfo;
import com.victor.pojo.PageResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询员工
     * @return 分页结果
     */
    PageResult<Emp> page(EmpQueryParam queryParam);

    /**
     * 新增员工信息
     * @param emp
     */
    void save(Emp emp) throws Exception;

    /**
        删除员工信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工的详细信息
     */
    Emp getInfo(Integer id);


    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 员工登录方法
     */
    LoginInfo login(Emp emp);
}
