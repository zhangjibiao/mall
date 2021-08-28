package cn.stu.mall.ums.mapper;

import cn.stu.mall.ums.api.entity.UmsMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author 张积标
 * @since 2021-08-26
 */
@Mapper
public interface UmsMemberMapper extends BaseMapper<UmsMember> {

    int selectByUsername(UmsMember umsMember);
    List<UmsMember> findAll();
}
