package cn.stu.mall.ums.api.entity.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class UmsMemberRegisterParamDTO {
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;
}
