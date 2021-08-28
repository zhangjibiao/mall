package cn.stu.mall.portal.web.md5;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class EncodeOrDecoderTest {

    @Test
    void md5(){
        String string = "hhh";
        String a = DigestUtils.md5DigestAsHex(string.getBytes());
        System.out.println(a);
    }

    @Test
    void bcrypt(){
        String source = "hhh";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String miwen1 = encoder.encode(source);
        String miwen2 = encoder.encode(source);
        System.out.println("密文1:"+miwen1);
        System.out.println("密文2:"+miwen2);
        System.out.println(encoder.matches(source, miwen1));
        System.out.println(encoder.matches(source, miwen2));

    }
}
