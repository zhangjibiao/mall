package cn.stu.mall.ums.api.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UmsMemberChangeParamDTO {
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;
}
