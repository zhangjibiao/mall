package cn.stu.mall.portal.web.controller;

import com.baomidou.kaptcha.Kaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/kCaptcha")
public class KCaptchaController {
    @Autowired
    private Kaptcha kaptcha;

    @RequestMapping("generator")
    public void generator(HttpServletRequest request, HttpServletResponse response){
        kaptcha.render();
    }

    @RequestMapping("verify")
    public String verify(String code, HttpServletRequest request){
        if (kaptcha.validate(code)){
            return "验证通过";
        }else return "验证不通过";
    }
}
