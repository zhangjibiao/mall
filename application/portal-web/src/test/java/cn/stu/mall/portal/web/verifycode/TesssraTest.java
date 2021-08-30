package cn.stu.mall.portal.web.verifycode;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TesssraTest {
    @Test
    public void crack() throws TesseractException {
        ITesseract iTesseract = new Tesseract();

        iTesseract.setDatapath("D:");
        iTesseract.setLanguage("chi_sim");

        File file = new File("D:\\generator.jpg");
        String s = iTesseract.doOCR(file);
        System.out.println(s);

        System.out.println(iTesseract.doOCR(new File("D:\\1.png")));
    }
}
