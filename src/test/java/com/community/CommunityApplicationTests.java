package com.community;

import com.community.entity.domain.User;
import com.community.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    IUserService iUserService;
    @Test
    void contextLoads() {
        User u = new User();
        u.setName("1111");//字段设置类型问题
        u.setAccountId("45454");
        iUserService.save(u);
        System.out.println(u);
    }

}
