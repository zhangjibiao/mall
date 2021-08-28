package cn.stu.mall.ums.api;


import cn.stu.mall.ums.api.entity.UmsMember;
import cn.stu.mall.ums.api.entity.dto.UmsMemberRegisterParamDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author 晁鹏飞
 * @since 2020-12-23
 */
public interface UmsMemberService extends IService<UmsMember> {
    public String register(UmsMemberRegisterParamDTO u);

    boolean isRegister(UmsMemberRegisterParamDTO u);

    List<UmsMember> findAll();
}
