package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.community.entity.domain.Notification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.entity.vo.NotificationVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author du
 * @since 2020-03-18
 */
public interface INotificationService extends IService<Notification> {

    IPage<NotificationVO> getNotificationVO(Long id, Integer page, Integer size);
}
