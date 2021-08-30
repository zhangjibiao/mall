package cn.stu.mall.portal.web.util;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.DictionaryReader;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

public class JCaptchaUtil {
    private static final ImageCaptchaService service = imageCaptchaService();

    public static ImageCaptchaService getService(){
        return service;
    }

    private static ImageCaptchaService imageCaptchaService(){
        //背景
        UniColorBackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(100, 50);


        //字
        RandomRangeColorGenerator randomRangeColorGenerator = new RandomRangeColorGenerator(new int[]{0, 100}, new int[]{0, 100}, new int[]{20, 20});
        RandomTextPaster randomTextPaster = new RandomTextPaster(3,5,randomRangeColorGenerator.getNextColor());
        RandomFontGenerator randomFontGenerator = new RandomFontGenerator(20, 30);

        //组装图像
        ComposedWordToImage composedWordToImage = new ComposedWordToImage(randomFontGenerator,backgroundGenerator,randomTextPaster);
        ComposeDictionaryWordGenerator cdwd = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));

        GimpyFactory gf = new GimpyFactory(cdwd, composedWordToImage);

        GenericCaptchaEngine gce = new GenericCaptchaEngine(new CaptchaFactory[]{gf});

        return new GenericManageableCaptchaService(gce,180,
                200000,
                75000);
    }
}
