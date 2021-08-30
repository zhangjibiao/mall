package cn.stu.mall.portal.web.advice;

import cn.stu.mall.common.base.result.ResultWrapper;
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

    //使用自定义异常
    @ExceptionHandler()
    public ResultWrapper a(Exception e){
        e.printStackTrace();
        return ResultWrapper.builder().code(301).msg("自定义异常：token错误").build();
    }
}
