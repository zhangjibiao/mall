package cn.stu.mall.portal.web.controller;

import cn.stu.mall.portal.web.code.ImageCode;
import cn.stu.mall.portal.web.code.ImageCodeArith;
import cn.stu.mall.portal.web.util.JCaptchaUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/JCaptcha")
public class JCaptchaCodeController {
    @RequestMapping("/generator")
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getSession().getId();
        BufferedImage bufferedImage = JCaptchaUtil.getService().getImageChallengeForID(id);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream);

        jpegEncoder.encode(bufferedImage);

        response.setHeader("Cache-Control", "no-store");
        response.setContentType("image/jpeg");

        byte[] bytes = byteArrayOutputStream.toByteArray();
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping("/verify")
    public String verify(String verifiedCode, HttpServletRequest request) {
       Boolean aBoolean = JCaptchaUtil.getService().validateResponseForID(request.getSession().getId(), verifiedCode );
        if(aBoolean){
            return "验证码正确";
        }else {
            return "验证码错误";
        }
    }
}
