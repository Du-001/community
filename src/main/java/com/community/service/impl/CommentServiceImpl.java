package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.Result;
import com.community.entity.domain.Comment;
import com.community.entity.domain.Notification;
import com.community.entity.domain.Question;
import com.community.entity.domain.User;
import com.community.entity.enums.CommentTypeEnum;
import com.community.entity.enums.NotificationStatusEnum;
import com.community.entity.enums.NotificationTypeEnum;
import com.community.entity.vo.CommentVO;
import com.community.mapper.CommentMapper;
import com.community.mapper.NotificationMapper;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author du
 * @since 2020-03-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    @Transactional
    public Result saveComment(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            return Result.error("未选中任何问题或评论");
        }
        if (comment.getParentId() == null || !CommentTypeEnum.isExist(comment.getType())) {
            return Result.error("评论类型错误或不存在");
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            Comment comment1 = getById(comment.getParentId());
            if (comment1 == null) {
                return Result.error("评论不存在");
            }
            commentMapper.incSubCommentCount(comment.getParentId());
            createNotification(comment,NotificationTypeEnum.REPLY_COMMENT.getType(),comment1.getCommentator(),comment1.getParentId());
        } else {
            Question question = questionMapper.selectById(comment.getParentId());
            if (question == null) {
                return Result.error("评论的问题未找到");
            }
            questionMapper.incCommentCount(question.getId());
            createNotification(comment,NotificationTypeEnum.REPLY_QUESTION.getType(),question.getUserId(),question.getId());
        }
        save(comment);
        return Result.success();
    }
    private void createNotification(Comment comment,int type,Long receiver,Long questionId){
        Notification notification = new Notification();
        notification.setType(type);
        notification.setOuterId(questionId);
        notification.setNotifier(comment.getCommentator());
        notification.setReceiver(receiver);
        if(Objects.equals(receiver,comment.getCommentator())){
            notification.setStatus(NotificationStatusEnum.READ.getStatus());
        }else {
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        }
        notificationMapper.insert(notification);
    }

    @Override
    public List<CommentVO> getCommentVO(Long id, Integer type) {
        List<Comment> comments = list(new QueryWrapper<Comment>().eq("parent_id", id).eq("type", type).orderByDesc("create_time"));
        if (comments == null || comments.size() == 0) {
            return null;
        }
        Set<Long> userIds = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<User> users = userMapper.selectBatchIds(userIds);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentVO> commentVOS = comments.stream().map(comment -> {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            commentVO.setUser(userMap.get(comment.getCommentator()));
            return commentVO;
        }).collect(Collectors.toList());
        return commentVOS;
    }
}
