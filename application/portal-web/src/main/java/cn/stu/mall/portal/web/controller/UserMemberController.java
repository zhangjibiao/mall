package cn.stu.mall.portal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-member")
public class UserMemberController {
    @Autowired


    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}
