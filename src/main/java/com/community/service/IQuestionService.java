package com.community.service;

import com.community.entity.domain.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.entity.vo.QuestionVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author du
 * @since 2020-03-12
 */
public interface IQuestionService extends IService<Question> {

    List<QuestionVO> questionVOList();

    List<QuestionVO> questionVOListByUserId(Long id);

    QuestionVO getQuestionVOById(Long id);
}
