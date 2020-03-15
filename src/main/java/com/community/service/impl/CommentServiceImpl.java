package com.community.service.impl;

import com.community.entity.Result;
import com.community.entity.domain.Comment;
import com.community.entity.domain.Question;
import com.community.entity.enums.CommentTypeEnum;
import com.community.mapper.CommentMapper;
import com.community.mapper.QuestionMapper;
import com.community.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        } else {
            Question question = questionMapper.selectById(comment.getParentId());
            if (question == null) {
                return Result.error("评论的问题未找到");
            }
            questionMapper.addCommentCount(question.getId());
        }
        save(comment);
        return Result.success();
    }
}
