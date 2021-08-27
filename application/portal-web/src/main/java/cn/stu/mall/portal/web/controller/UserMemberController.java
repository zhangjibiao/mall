package cn.stu.mall.portal.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-member")
public class UserMemberController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
