package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.entity.domain.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.entity.dto.QuestionDTO;
import com.community.entity.vo.QuestionVO;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author du
 * @since 2020-03-12
 */
public interface IQuestionService extends IService<Question> {

    QuestionVO getQuestionVOById(Long id);

    QuestionDTO getQuestionDTOById(Long questionId);

    Page<QuestionVO> questionVOList(String search, Integer page, Integer size);

    List<Question> getRelatedQuestion(QuestionVO question);

    Page<QuestionVO> questionVOListByUserId(Long id, Integer page, Integer size);
}
