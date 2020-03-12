package com.community.service.impl;

import com.community.entity.domain.Question;
import com.community.mapper.QuestionMapper;
import com.community.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author du
 * @since 2020-03-12
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
