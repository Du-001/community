package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.domain.Tag;
import com.community.entity.vo.TagVO;
import com.community.mapper.TagMapper;
import com.community.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<TagVO> getTag() {
        List<Tag> tags = list(new QueryWrapper<Tag>().eq("parent_id", 0));
        if (tags == null || tags.size() == 0) {
            return null;
        }
        List<TagVO> tagVOS = tags.stream().map(tag -> {
            TagVO tagVO = new TagVO();
            BeanUtils.copyProperties(tag,tagVO);
            List<Tag> tagList = list(new QueryWrapper<Tag>().eq("parent_id", tag.getId()));
            tagVO.setTags(tagList);
            return tagVO;
        }).collect(Collectors.toList());
        return tagVOS;
    }
}
