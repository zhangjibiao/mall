package cn.stu.mall.ca.web2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//启动类
//和controller包同一个层次即可
@SpringBootApplication
@RestController
public class CaWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaWebApplication.class, args); // 注意这个class对象是这个类，不是固定的
    }

    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
