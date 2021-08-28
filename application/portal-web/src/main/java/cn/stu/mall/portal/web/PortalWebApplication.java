package cn.stu.mall.portal.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.stu.mall"})
public class PortalWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalWebApplication.class, args);
    }
}
