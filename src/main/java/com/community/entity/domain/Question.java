package com.community.entity.domain;

import com.community.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author du
 * @since 2020-03-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Question extends BaseEntity {

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 发起人
     */
    private Long userId;

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


}
