package cn.stu.mall.ums;

import cn.stu.mall.ums.entity.UmsMember;
import cn.stu.mall.ums.mapper.UmsMemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    UmsMemberMapper mapper;

    @Test
    void insertTest(){
        UmsMember member = new UmsMember();
        member.setId(22L);
        member.setEmail("email");
        member.setPassword("23");
        member.setUsername("zhanao");
        member.setStatus(1);
        mapper.insert(member);
    }

    @Test
    void testUpdate(){
        UmsMember member = new UmsMember();
        member.setId(64L);
        member.setPassword("45");
        mapper.updateById(member);
    }
}
