package com.victor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {

    /**
     * 生成JWT令牌
     */
    @Test
    public void testGenerateJwt() {
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("name","victor");

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "dmljdG9y") //指定加密算法，密钥
                .addClaims(dataMap)//添加自定义声明
                .setExpiration(new Date(System.currentTimeMillis() + 36000 * 1000))//设置过期时间
                .compact();//生成JWT令牌

        System.out.println(jwt);
    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void testParseJwt() {
        String token = "";
        Claims claims = Jwts.parser().setSigningKey("dmljdG9y")//指定密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidmljdG9yIiwiaWQiOjEsImV4cCI6MTc3Njg1MDM2MH0.-Cl_w6BwUbc6KP_d8FAQDoGSOLJt0Vb7qpy6hDTGIkY")//解析令牌
                .getBody();//获取自定义信息

        System.out.println(claims);
    }

}
