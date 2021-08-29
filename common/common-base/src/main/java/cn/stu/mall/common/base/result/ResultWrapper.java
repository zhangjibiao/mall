package cn.stu.mall.common.base.result;

import cn.stu.mall.common.base.enums.StateCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class ResultWrapper<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static ResultWrapper.ResultWrapperBuilder getSuccessBuilder(){
        return ResultWrapper.builder().code(StateCodeEnum.SUCCESS.getCode())
                .msg(StateCodeEnum.SUCCESS.getMsg());
    }

    public static ResultWrapper.ResultWrapperBuilder getFailBuilder(){
        return ResultWrapper.builder().code(StateCodeEnum.FAIL.getCode())
                .msg(StateCodeEnum.FAIL.getMsg());
    }
}
