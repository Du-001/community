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
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Notification extends BaseEntity {

    /**
     * 提醒人id
     */
    private Long notifier;

    /**
     * 接收人id
     */
    private Long receiver;

    /**
     * 问题id
     */
    private Long questionId;

    /**
     * 提醒内容id
     */
    private Long outerId;

    /**
     * 提醒类型
     */
    private Integer type;

    /**
     * 是否已读
     */
    private Integer status;

}
