package cn.stu.mall.portal.web.controller;

import cn.stu.mall.portal.web.code.ImageCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

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


    @RequestMapping("/verify")
    public String verify(String verifiedCode, HttpServletRequest request){
        Object o = request.getSession().getAttribute("attrName");
        if(o == null){
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
