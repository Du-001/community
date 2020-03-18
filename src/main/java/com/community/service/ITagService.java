package com.community.service;

import com.community.entity.domain.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author du
 * @since 2020-03-17
 */
public interface ITagService extends IService<Tag> {
    List<Tag> getTag();
}
