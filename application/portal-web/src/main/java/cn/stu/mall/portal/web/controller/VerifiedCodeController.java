package cn.stu.mall.portal.web.controller;

import cn.stu.mall.portal.web.code.ImageCode;
import cn.stu.mall.portal.web.code.ImageCodeArith;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/code")
public class VerifiedCodeController {
    @RequestMapping("/generator")
    public void generatorCode(HttpServletRequest request, HttpServletResponse response){
        try {
            ImageCode imageCode = ImageCode.getInstance();

            String code = imageCode.getCode();
            request.getSession().setAttribute("attrName", code);
            ByteArrayInputStream image = imageCode.getImage();
            response.setContentType("image/jpeg");
            byte[] bytes = new byte[1024];
            try (ServletOutputStream out = response.getOutputStream();){
                while (image.read(bytes) != -1){
                    out.write(bytes);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/generator-base64")
    public String generatorCodeBase64(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ImageCodeArith imageCode = ImageCodeArith.getInstance();
//            ImageCode imageCode = ImageCode.getInstance();

            String code = imageCode.getCode();
            request.getSession().setAttribute("attrName", code);

            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            ByteArrayInputStream image = imageCode.getImage();

            byte[] buffer = new byte[1024];
            int r = 0;
            while ((r=image.read(buffer, 0, 1024)) > 0){
                swapStream.write(buffer, 0, r);
            }

            byte[] data = swapStream.toByteArray();
            return Base64.getEncoder().encodeToString(data);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

    @RequestMapping("/verify")
    public String verify(String verifiedCode, HttpServletRequest request){
        Object o = request.getSession().getAttribute("attrName");
        System.out.println("正确的验证码："+o.toString());
        System.out.println("你输入的验证码:"+verifiedCode);
        if(null == o){
            return "没有检验码";
        }
        String code = request.getSession().getAttribute("attrName").toString();
        if (code.equals(verifiedCode)){
            return "校验通过";
        }else {
            return "校验不通过";
        }
    }
}
