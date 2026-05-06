package com.victor.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {
    private String name;
    private Integer degree;
    private Integer classId;
    private Integer page;
    private Integer pageSize;
}
