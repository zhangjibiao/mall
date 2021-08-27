package cn.stu.mall.ums.serviceImpl;



import cn.stu.mall.ums.api.UmsMemberService;
import cn.stu.mall.ums.api.entity.UmsMember;
import cn.stu.mall.ums.mapper.UmsMemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author 晁鹏飞
 * @since 2020-12-23
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, cn.stu.mall.ums.api.entity.UmsMember> implements UmsMemberService {
    @Autowired
    UmsMemberMapper mapper;

    @Override
    public String register(){
        UmsMember u = new UmsMember();
        u.setUsername("lishi");
        mapper.insert(u);
        return "success";
    }
}
