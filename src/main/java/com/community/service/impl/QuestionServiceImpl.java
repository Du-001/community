package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.domain.Question;
import com.community.entity.vo.QuestionVO;
import com.community.mapper.QuestionMapper;
import com.community.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private IUserService iUserService;
    @Override
    public List<QuestionVO> questionVOList() {
        List<QuestionVO> questionVOS = new ArrayList<>();
        List<Question> questions = list();
        for (Question question : questions){
            QuestionVO questionVO = new QuestionVO();
            BeanUtils.copyProperties(question, questionVO);
            questionVO.setUser(iUserService.getById(question.getUserId()));
            questionVOS.add(questionVO);
        }
        return questionVOS;
    }

    @Override
    public List<QuestionVO> questionVOListByUserId(Long id) {
        List<QuestionVO> questionVOS = new ArrayList<>();
        List<Question> questions = list(new QueryWrapper<Question>().eq("user_id",id));
        for (Question question : questions){
            QuestionVO questionVO = new QuestionVO();
            BeanUtils.copyProperties(question, questionVO);
            questionVOS.add(questionVO);
        }
        return questionVOS;
    }

    @Override
    public QuestionVO getQuestionVOById(Long id) {
        QuestionVO questionVO = new QuestionVO();
        Question question = getById(id);
        BeanUtils.copyProperties(question,questionVO);
        questionVO.setUser(iUserService.getById(question.getUserId()));
        return questionVO;
    }
}
