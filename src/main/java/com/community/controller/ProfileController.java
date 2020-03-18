package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.community.entity.domain.Notification;
import com.community.entity.domain.User;
import com.community.entity.enums.NotificationStatusEnum;
import com.community.entity.vo.NotificationVO;
import com.community.entity.vo.QuestionVO;
import com.community.exception.CustomizeException;
import com.community.exception.emuns.CustomizeErrorCode;
import com.community.service.INotificationService;
import com.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.security.acl.LastOwnerException;

@Controller
public class ProfileController {

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private INotificationService iNotificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page" , defaultValue = "1") Integer page,
                          @RequestParam(name = "size" , defaultValue = "10") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_LOGIN);
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            IPage<QuestionVO> questionVOIPage = iQuestionService.questionVOListByUserId(user.getId(), page, size);
            model.addAttribute("questions", questionVOIPage);
        } else if ("replies".equals(action)) {
            IPage<NotificationVO> notificationVOIPage = iNotificationService.getNotificationVO(user.getId(),page,size);
            int unReadCount = iNotificationService.count(new QueryWrapper<Notification>().eq("notifier",user.getId()).eq("status", NotificationStatusEnum.READ.getStatus()));
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("unReadCount", unReadCount);
            model.addAttribute("questions", notificationVOIPage);
        }
        return "profile";
    }
}
