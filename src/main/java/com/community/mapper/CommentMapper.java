package com.community.mapper;

import com.community.entity.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author du
 * @since 2020-03-15
 */
public interface CommentMapper extends BaseMapper<Comment> {

    @Update("update comment set comment_count = comment_count + 1 where id = ${id}")
    void addSubCommentCount(@Param("id") Long id);
}
