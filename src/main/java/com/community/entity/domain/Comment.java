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
 * @since 2020-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Comment extends BaseEntity {

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

}
