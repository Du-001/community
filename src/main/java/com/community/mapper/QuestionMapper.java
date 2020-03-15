package com.community.mapper;

import com.community.entity.domain.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author du
 * @since 2020-03-12
 */
public interface QuestionMapper extends BaseMapper<Question> {

    @Update("update question set view_count = view_count + 1 where id = ${id}")
    void addViewCount(@Param("id") Long id);

    @Update("update question set comment_count = comment_count + 1 where id = ${id}")
    void addCommentCount(@Param("id") Long id);
}
