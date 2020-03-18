package com.community.entity.vo;

import com.community.entity.BaseEntity;
import com.community.entity.domain.User;
import lombok.Data;

@Data
public class NotificationVO extends BaseEntity {

    /**
     * 是否已读
     */
    private Integer status;

    /**
     * 回复人
     */
    private User notifier;

    /**
     * 通知
     */
    private String outerTitle;

    /**
     * 类型(notificationTypeEnum)
     */
    private String type;
}
