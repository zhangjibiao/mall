package cn.stu.mall.portal.web.advice;

import cn.stu.mall.common.base.result.ResultWrapper;
import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import javafx.fxml.LoadException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@ControllerAdvice
@ResponseBody
//@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(ArithmeticException.class)
    public ResultWrapper customException(Exception e){
        e.printStackTrace();
        return ResultWrapper.builder().code(301).msg("ArithmeticException").build();
    }

    @ExceptionHandler(LoginException.class)
    public ResultWrapper loginException(Exception e){
        e.printStackTrace();
        return ResultWrapper.builder().code(301).msg("登录错误").build();
    }

    //Kaptcha验证
    @ExceptionHandler(KaptchaException.class)
    public ResultWrapper a(Exception e){
        if (e instanceof KaptchaTimeoutException){
            return ResultWrapper.getFailBuilder().msg("验证码已超时").build();
        }else if (e instanceof KaptchaIncorrectException){
            return ResultWrapper.getFailBuilder().msg("验证码不正确").build();
        }else if(e instanceof NullPointerException){
            return ResultWrapper.getFailBuilder().msg("没有验证码").build();
        }else {
            return ResultWrapper.getFailBuilder().msg("KaptchaNotFoundException").build();

        }
    }
}
