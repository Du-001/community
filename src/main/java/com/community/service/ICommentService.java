package com.community.service;

import com.community.entity.Result;
import com.community.entity.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author du
 * @since 2020-03-15
 */
public interface ICommentService extends IService<Comment> {

    Result saveComment(Comment comment);
}
