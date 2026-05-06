package com.victor.service.impl;

import com.victor.mapper.EmpMapper;
import com.victor.mapper.StudentMapper;
import com.victor.pojo.JobOption;
import com.victor.pojo.StudentCountData;
import com.victor.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("number")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public StudentCountData getStudentCountData() {
        List<Map<String,Object>> list2 = studentMapper.countStudentCountData();
        List<Object> classList = list2.stream().map(dataMap -> dataMap.get("classList")).toList();
        List<Object> dataList = list2.stream().map(dataMap -> dataMap.get("dataList")).toList();
        return new StudentCountData(classList, dataList);
    }

    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }
}
