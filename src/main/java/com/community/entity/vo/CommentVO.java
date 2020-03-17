package com.community.entity.vo;

import com.community.entity.BaseEntity;
import com.community.entity.domain.Comment;
import com.community.entity.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentVO extends BaseEntity {

    /**
     * 父类id
     */
    private Long parentId;

    /**
     * 父类类型
     */
    private Integer type;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 评论人
     */
    private Long commentator;

    /**
     * 子评论数
     */
    private Integer commentCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 发起人
     */
    private User user;

}
