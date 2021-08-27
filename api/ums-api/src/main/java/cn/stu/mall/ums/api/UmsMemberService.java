package cn.stu.mall.ums.api;


import cn.stu.mall.ums.api.entity.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author 晁鹏飞
 * @since 2020-12-23
 */
public interface UmsMemberService extends IService<UmsMember> {
    public String register();
}
