package cn.stu.mall.portal.web.controller;

import cn.stu.mall.common.base.annotations.TokenCheck;
import cn.stu.mall.common.base.result.ResultWrapper;
import cn.stu.mall.common.utils.JwtUtil;
import cn.stu.mall.ums.api.UmsMemberService;
import cn.stu.mall.ums.api.entity.UmsMember;
import cn.stu.mall.ums.api.entity.dto.UmsMemberChangeParamDTO;
import cn.stu.mall.ums.api.entity.dto.UmsMemberLoginParamDTO;
import cn.stu.mall.ums.api.entity.dto.UmsMemberRegisterParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.ws.ResponseWrapper;
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

    @RequestMapping
    public String getCaptcha(){
        return "获取验证码";
    }

    @RequestMapping("register")
    public ResultWrapper register(@RequestBody @Valid UmsMemberRegisterParamDTO u){
        if (service.isRegister(u)){
            return ResultWrapper.getFailBuilder().data("用户名重复，请换一个！").build();
        }else {
            service.register(u);
            return ResultWrapper.getSuccessBuilder().data(u.getUsername() + "  register").build();
        }
    }

    @RequestMapping("login")
    public ResultWrapper login(@RequestBody UmsMemberLoginParamDTO u){
        return service.login(u);
    }

    @RequestMapping("findAll")
    public String findAll(){
        List<UmsMember> list = service.findAll();
        System.out.println(Arrays.toString(list.toArray()));
        return "success";
    }

    @RequestMapping("verify")
    public ResultWrapper verify(String token){
        String s = JwtUtil.parseToken(token);
        return ResultWrapper.getSuccessBuilder().data(s).build();
    }

    @RequestMapping("modified")
    @TokenCheck
    public ResultWrapper modified(@RequestBody UmsMemberChangeParamDTO u){
        return service.modified(u);
    }



}
