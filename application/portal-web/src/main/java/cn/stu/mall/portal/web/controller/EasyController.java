package cn.stu.mall.portal.web.controller;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("easy")
public class EasyController {
    @RequestMapping("/generator")
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtil.out(request, response);
    }

    @RequestMapping("/verify")
    public String verify(String verifiedCode, HttpServletRequest request) {
        boolean success = CaptchaUtil.ver(verifiedCode,request);
        if(success){
            HappyCaptcha.remove(request);
            return "通过";
        }else {
            return "不通过";
        }
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //redis没干完，
    @RequestMapping("/generator-redis")
    public void generatorCodeRedis(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SpecCaptcha specCaptcha = new SpecCaptcha(100,50);
        String text = specCaptcha.text();
        System.out.println("验证码：" + text);

        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set("c", text);
        CaptchaUtil.out(specCaptcha,request, response);
    }


    @RequestMapping("/verify-redis")
    public String verifyRedis(String verifiedCode, HttpServletRequest request) {
        String c = stringRedisTemplate.opsForValue().get("c");
        boolean success = CaptchaUtil.ver(verifiedCode,request);
        if(verifiedCode.equals(c)){
            HappyCaptcha.remove(request);
            return "通过";
        }else {
            return "不通过";
        }
    }
}
