package cn.stu.mall.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtil {
    private static final String secret = "Lucky";
    public static String getToken(String subject){
        String token = Jwts.builder().setSubject(subject)
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 10*10))
                .signWith(SignatureAlgorithm.HS256, secret).
                        compact();
        return token;
    }

    public static String parseToken(String token){
        System.out.println("传入的token\n"+token);
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String subject = body.getSubject();
        return subject;
    }

    public static void main(String[] args) throws InterruptedException {
        String name = "zhangsan";
        String token = getToken(name);
        System.out.println("------------使用内部的来解析--------");
        String s = parseToken(token);
        System.out.println("------------输出得到的原文----------");
        System.out.println(s);
    }
}
