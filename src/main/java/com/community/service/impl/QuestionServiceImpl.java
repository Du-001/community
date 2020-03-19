package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.entity.domain.Question;
import com.community.entity.domain.User;
import com.community.entity.dto.QuestionDTO;
import com.community.entity.vo.PageVO;
import com.community.entity.vo.QuestionVO;
import com.community.exception.CustomizeException;
import com.community.exception.emuns.CustomizeErrorCode;
import com.community.mapper.QuestionMapper;
import com.community.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author du
 * @since 2020-03-12
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Page<QuestionVO> questionVOList(Integer page, Integer size) {
        Page<Question> questionPage = page(new Page<>(page, size), new QueryWrapper<Question>().orderByDesc("create_time"));
        PageVO<QuestionVO> questionVOPage = new PageVO<>(page, size, questionPage.getTotal());
        for (Question question : questionPage.getRecords()) {
            QuestionVO questionVO = new QuestionVO();
            BeanUtils.copyProperties(question, questionVO);
            questionVO.setUser(iUserService.getById(question.getUserId()));
            questionVOPage.getRecords().add(questionVO);
        }
        questionVOPage.init();
        return questionVOPage;
    }

    @Override
    public List<Question> getRelatedQuestion(QuestionVO question) {
        String tag = question.getTag();
        if (StringUtils.isEmpty(tag)) {
            return null;
        }
        String reg = StringUtils.replaceChars(tag, ',', '|');
        return questionMapper.getRelatedQuestion(reg, question.getId());
    }

    @Override
    public Page<QuestionVO> questionVOListByUserId(Long id, Integer page, Integer size) {
        Page<Question> questionPage = page(new Page<>(page, size), new QueryWrapper<Question>().eq("user_id", id).orderByDesc("create_time"));
        PageVO<QuestionVO> questionVOPage = new PageVO<>(page, size, questionPage.getTotal());
        User user = iUserService.getById(id);
        questionVOPage.setRecords(new ArrayList<>());
        for (Question question : questionPage.getRecords()) {
            QuestionVO questionVO = new QuestionVO();
            BeanUtils.copyProperties(question, questionVO);
            questionVO.setUser(user);
            questionVOPage.getRecords().add(questionVO);
        }
        questionVOPage.init();
        return questionVOPage;
    }

    @Override
    public QuestionVO getQuestionVOById(Long id) {
        QuestionVO questionVO = new QuestionVO();
        Question question = getById(id);
        questionMapper.incViewCount(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        BeanUtils.copyProperties(question, questionVO);
        questionVO.setUser(iUserService.getById(question.getUserId()));
        return questionVO;
    }


    @Override
    public QuestionDTO getQuestionDTOById(Long questionId) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = getById(questionId);
        BeanUtils.copyProperties(question, questionDTO);
        return questionDTO;
    }
}
