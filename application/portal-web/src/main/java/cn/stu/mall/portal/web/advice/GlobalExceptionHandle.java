package cn.stu.mall.portal.web.advice;

import cn.stu.mall.common.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
//@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ResultWrapper customException(){
        return ResultWrapper.builder().code(301).msg("统一异常").build();
    }
}
