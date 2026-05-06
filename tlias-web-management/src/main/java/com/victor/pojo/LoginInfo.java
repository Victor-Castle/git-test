package com.victor.pojo;


/**
 * 封装登录结果
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private Integer id;
    private String name;
    private String username;
    private String token;

}
