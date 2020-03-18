package com.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.entity.domain.Notification;
import com.community.entity.domain.User;
import com.community.entity.enums.NotificationStatusEnum;
import com.community.entity.vo.QuestionVO;
import com.community.service.INotificationService;
import com.community.service.IQuestionService;
import com.community.utils.JwtIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private INotificationService iNotificationService;

    @JwtIgnore
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @JwtIgnore
    @GetMapping("/")
    public String index(Model model,
                        HttpServletRequest request,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<QuestionVO> questions = iQuestionService.questionVOList(page, size);
        User user = (User) request.getSession().getAttribute("user");
        Integer unReadCount = null;
        if (user != null) {
            unReadCount = iNotificationService.count(new QueryWrapper<Notification>().eq("notifier", user.getId()).eq("status", NotificationStatusEnum.READ.getStatus()));
        }
        model.addAttribute("questions", questions);
        model.addAttribute("unReadCount", unReadCount);
        return "index";
    }
}
