package cn.stu.mall.ums.api.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class UmsMemberRegisterParamDTO {
    @NotEmpty(message = "用户名不能为空")
    //@Max(value = 20,message = "用户名不能超过20位")
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;
}
