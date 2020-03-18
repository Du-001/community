package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.domain.Tag;
import com.community.mapper.TagMapper;
import com.community.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author du
 * @since 2020-03-17
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Override
    public List<Tag> getTag() {
        List<Tag> tags = list(new QueryWrapper<Tag>().eq("parent_id", 0));
        if (tags == null || tags.size() == 0) {
            return null;
        }
        List<Tag> t = tags.stream().map(tag -> {
            List<Tag> tagList = list(new QueryWrapper<Tag>().eq("parent_id", tag.getId()));
            tag.setSubTags(tagList);
            return tag;
        }).collect(Collectors.toList());
        return t;
    }
}
