package com.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.domain.Comment;
import com.community.entity.domain.Notification;
import com.community.entity.domain.Question;
import com.community.entity.domain.User;
import com.community.entity.enums.CommentTypeEnum;
import com.community.entity.enums.NotificationStatusEnum;
import com.community.entity.enums.NotificationTypeEnum;
import com.community.entity.vo.CommentVO;
import com.community.entity.vo.QuestionVO;
import com.community.exception.CustomizeException;
import com.community.exception.emuns.CustomizeErrorCode;
import com.community.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author du
 * @since 2020-03-18
 */
@Controller
public class NotificationController {

    @Autowired
    private INotificationService iNotificationService;

    @GetMapping("/notification/{id}")
    public String notification(@PathVariable(name = "id") Long id,
                               HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        Notification notification = iNotificationService.getById(id);
        if(notification==null){
            throw new CustomizeException("消息不见了!");
        }
        if (!Objects.equals(notification.getReceiver(),user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.RECEIVER_ERROR);
        }
        Notification newNotify = new Notification();
        newNotify.setId(id);
        newNotify.setStatus(NotificationStatusEnum.READ.getStatus());
        iNotificationService.updateById(newNotify);
        return "redirect:/question/"+notification.getOuterId();
    }
}

