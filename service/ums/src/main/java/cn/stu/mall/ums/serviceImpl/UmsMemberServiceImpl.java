package cn.stu.mall.ums.serviceImpl;



import cn.stu.mall.common.utils.JwtUtil;
import cn.stu.mall.ums.api.UmsMemberService;
import cn.stu.mall.ums.api.entity.UmsMember;
import cn.stu.mall.ums.api.entity.dto.UmsMemberLoginParamDTO;
import cn.stu.mall.ums.api.entity.dto.UmsMemberRegisterParamDTO;
import cn.stu.mall.ums.mapper.UmsMemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public String register(UmsMemberRegisterParamDTO u){
        UmsMember umsMember = new UmsMember();

        // 加密密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(u.getPassword());
        u.setPassword(encoded);

        BeanUtils.copyProperties(u, umsMember);
        mapper.insert(umsMember);
        return "success";
    }

    @Override
    public boolean isRegister(UmsMemberRegisterParamDTO u) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(u, umsMember);
        return mapper.selectByUsername(umsMember) >= 1;
    }

    @Override
    public List<UmsMember> findAll(){
        return mapper.findAll();
    }

    @Override
    public String login(UmsMemberLoginParamDTO u){
        UmsMember umsMember = mapper.selectByName(u.getUsername());
        if (null == umsMember){
            return "用户名不存在！";
        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(u.getPassword(),umsMember.getPassword())){
                String token = JwtUtil.getToken(umsMember.getUsername());
                System.out.println(umsMember.getUsername()+"   登录成功");
                return token;
            }else {
                return "密码不正确";
            }
        }
    }


}
