package com.victor.service;

import com.victor.pojo.JobOption;
import com.victor.pojo.StudentCountData;

import java.util.List;
import java.util.Map;

public interface ReportService {

    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();


    /**
     * 统计员工性别信息
     */
    List<Map> getEmpGenderData();

    StudentCountData getStudentCountData();

    List<Map> getStudentDegreeData();
}
