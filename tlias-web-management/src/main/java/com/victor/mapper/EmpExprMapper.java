package com.victor.mapper;

import com.victor.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//员工工作经历的Mapper接口
public interface EmpExprMapper {
    /*
     * 批量插入员工工作经历
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工的ID批量删除工作经历信息
     */
    void deleteByEmpIds(List<Integer> empIds);
}
