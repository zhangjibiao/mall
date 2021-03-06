package cn.stu.mall.ums.serviceImpl;



import cn.stu.mall.common.base.result.ResultWrapper;
import cn.stu.mall.common.utils.JwtUtil;
import cn.stu.mall.ums.api.UmsMemberService;
import cn.stu.mall.ums.api.entity.UmsMember;
import cn.stu.mall.ums.api.entity.dto.UmsMemberChangeParamDTO;
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
    public ResultWrapper login(UmsMemberLoginParamDTO u){
        UmsMember umsMember = mapper.selectByName(u.getUsername());
        if (null == umsMember){
            return ResultWrapper.getFailBuilder().data("用户名不存在！").build();
        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(u.getPassword(),umsMember.getPassword())){
                String token = JwtUtil.getToken(umsMember.getUsername());
                System.out.println(umsMember.getUsername()+"   登录成功");
                return ResultWrapper.getSuccessBuilder().data(token).build();
                //还得加上用户实体类类型
            }else {
                return ResultWrapper.getFailBuilder().data("密码不正确！").build();
            }
        }
    }

    @Override
    public ResultWrapper modified(UmsMemberChangeParamDTO u){
        UmsMember umsMember = mapper.selectByName(u.getUsername());

        BeanUtils.copyProperties(u, umsMember);
        // 加密密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(umsMember.getPassword());
        umsMember.setPassword(encoded);

        System.out.println(umsMember.toString());
        mapper.updateById(umsMember);
        return ResultWrapper.getSuccessBuilder().data("修改成功").build();
    }
}
