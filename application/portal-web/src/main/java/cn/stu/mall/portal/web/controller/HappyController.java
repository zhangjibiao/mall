package cn.stu.mall.portal.web.controller;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
@RequestMapping("happy-captcha")
public class HappyController {
    @RequestMapping("/generator")
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HappyCaptcha.require(request, response).
                style(CaptchaStyle.ANIM).
                type(CaptchaType.ARITHMETIC_ZH).build().finish();
    }

    @RequestMapping("/verify")
    public String verify(String verifiedCode, HttpServletRequest request) {
        System.out.println();
        boolean success = HappyCaptcha.verification(request, verifiedCode, true);
        if(success){
            HappyCaptcha.remove(request);
            return "通过";
        }else {
            return "不通过";
        }
    }
}
