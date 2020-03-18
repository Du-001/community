package com.community;

import com.community.entity.domain.Question;
import com.community.entity.domain.User;
import com.community.mapper.QuestionMapper;
import com.community.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.expression.Numbers;

import java.util.List;
import java.util.Locale;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    QuestionMapper questionMapper;
    @Test
    void contextLoads() {
//        User u = new User();
//        u.setName("1111");//字段设置类型问题
//        u.setAccountId("45454");
//        iUserService.save(u);
//        System.out.println(u);

    }

}
