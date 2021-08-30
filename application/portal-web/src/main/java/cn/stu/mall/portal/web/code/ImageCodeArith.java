package cn.stu.mall.portal.web.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

@Getter
@Setter
public class ImageCodeArith {
    private String code = "";
    public static String attrName = "verifyCode";

    private ByteArrayInputStream image;

    private int width = 400;
    private int height = 100;

    public  static ImageCodeArith getInstance() throws IOException {
        return new ImageCodeArith();
    }
    private ImageCodeArith() throws IOException {
        // 图像缓冲区
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        //给你一支笔
        Graphics graphics = image.getGraphics();

        //画长方形
        graphics.fillRect(0, 0, width, height);

        //画数字
        graphics.setColor(new Color(17,147,37));
        graphics.setFont(new Font("宋体", Font.PLAIN, 30));

        Random random = new Random();
        int num1 = random.nextInt(20);
        int num2 = random.nextInt(20);
        this.code = String.valueOf(num1+num2);

        graphics.drawString(String.valueOf(num1), width/6*0+20, 30);
        graphics.drawString("+", width/6*1+20, 30);
        graphics.drawString(String.valueOf(num2), width/6*2+20, 30);
        graphics.drawString("=", width/6*3+20, 30);
        graphics.drawString("?", width/6*4+20, 30);

        //收笔
        graphics.dispose();

        ByteArrayInputStream inputStream = null;
        ByteOutputStream outputStream = new ByteOutputStream();

        try {
            //赋值给图片
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(image, "jpeg", imageOutputStream);

            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        }catch (Exception e){
            System.out.println("生成验证码失败");
        }

        this.image = inputStream;

    }


}
