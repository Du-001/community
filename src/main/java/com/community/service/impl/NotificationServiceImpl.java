package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.entity.domain.Comment;
import com.community.entity.domain.Notification;
import com.community.entity.domain.Question;
import com.community.entity.domain.User;
import com.community.entity.enums.NotificationTypeEnum;
import com.community.entity.vo.NotificationVO;
import com.community.entity.vo.QuestionVO;
import com.community.mapper.NotificationMapper;
import com.community.service.ICommentService;
import com.community.service.INotificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.service.IQuestionService;
import com.community.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author du
 * @since 2020-03-18
 */
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements INotificationService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICommentService iCommentService;

    @Autowired
    private IQuestionService iQuestionService;

    @Override
    public IPage<NotificationVO> getNotificationVO(Long id, Integer page, Integer size) {
        Page<NotificationVO> notificationVOPage = new Page<>(page, size);
        Page<Notification> notificationPage = page(new Page<>(page, size), new QueryWrapper<Notification>().eq("notifier", id).orderByDesc("create_time"));
        notificationVOPage.setTotal(notificationPage.getTotal());
        notificationVOPage.setRecords(new ArrayList<>());

        Set<Long> notifiers = notificationPage.getRecords().stream().map(notification -> notification.getNotifier()).collect(Collectors.toSet());
        if (notifiers.size() > 0) {
            List<User> users = iUserService.listByIds(notifiers);
            Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
            for (Notification notification : notificationPage.getRecords()) {
                NotificationVO notificationVO = new NotificationVO();
                BeanUtils.copyProperties(notification, notificationVO);
                notificationVO.setNotifier(userMap.get(notification.getNotifier()));
                notificationVO.setType(NotificationTypeEnum.getName(notification.getType()));
                if (notification.getType() == NotificationTypeEnum.REPLY_COMMENT.getType()) {
                    Comment comment = iCommentService.getById(notification.getOuterId());
                    notificationVO.setOuterTitle(comment.getContent());
                } else {
                    Question question = iQuestionService.getById(notification.getOuterId());
                    notificationVO.setOuterTitle(question.getTitle());
                }
                notificationVOPage.getRecords().add(notificationVO);
            }
        }
        return notificationVOPage;
    }
}
