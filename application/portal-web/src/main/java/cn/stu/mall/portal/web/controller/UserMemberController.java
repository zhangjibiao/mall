package cn.stu.mall.portal.web.controller;

import cn.stu.mall.ums.api.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-member")
public class UserMemberController {
    @Autowired
    UmsMemberService service;


    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("register")
    public String register(){
        service.register();
        return "register";
    }
}
