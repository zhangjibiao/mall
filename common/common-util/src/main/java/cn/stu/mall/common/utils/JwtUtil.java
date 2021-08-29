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
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 10*10)).
                        signWith(SignatureAlgorithm.HS256, secret).
                        compact();
        return token;
    }

    public static String parseToken(String token){
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String subject = body.getSubject();
        return subject;
    }

    public static void main(String[] args) throws InterruptedException {
        String name = "zhangjibiao";
        String token = getToken(name);
        System.out.println(token);
        System.out.println("-----------立马解析");
        String s = parseToken(token);
        System.out.println(s);

        System.out.println("----------四秒后解析");
        TimeUnit.SECONDS.sleep(4);
        s = parseToken(token);
        System.out.println(s);

        System.out.println("----------13秒后解析");
        TimeUnit.SECONDS.sleep(9);
        s = parseToken(token);
        System.out.println(s);
    }
}
