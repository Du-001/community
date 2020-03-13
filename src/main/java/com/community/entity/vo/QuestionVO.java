package com.community.entity.vo;

import com.community.entity.BaseEntity;
import com.community.entity.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionVO extends BaseEntity {

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 阅读数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 标签
     */
    private String tag;

    /**
     * 发起人
     */
    private User user;

}
