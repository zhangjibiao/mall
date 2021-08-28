package cn.stu.mall.portal.web.controller;

import cn.stu.mall.ums.api.UmsMemberService;
import cn.stu.mall.ums.api.entity.UmsMember;
import cn.stu.mall.ums.api.entity.dto.UmsMemberLoginParamDTO;
import cn.stu.mall.ums.api.entity.dto.UmsMemberRegisterParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
    public String register(@RequestBody UmsMemberRegisterParamDTO u){
        if (service.isRegister(u)){
            return "用户名重复，请换一个！";
        }else {
            service.register(u);
            return "register";
        }
    }

    @RequestMapping("login")
    public String login(@RequestBody UmsMemberLoginParamDTO u){
        return service.login(u);
    }

    @RequestMapping("findAll")
    public String findAll(){
        List<UmsMember> list = service.findAll();
        System.out.println(Arrays.toString(list.toArray()));
        return "success";
    }


}
