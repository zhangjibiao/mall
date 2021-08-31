package cn.stu.mall.portal.web.controller;

import cn.stu.mall.portal.web.util.SliderUtil;
import cn.stu.mall.portal.web.util.VerificationVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("slider")
@RestController
public class MySliderController {
    @RequestMapping("generator")
    public VerificationVO generator(HttpServletRequest request, HttpServletResponse response){
        return SliderUtil.cut();
    }

    @RequestMapping("verify")
    public String verify(String code, HttpServletRequest request) {
        return null;
    }
}
