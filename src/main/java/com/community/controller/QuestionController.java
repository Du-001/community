package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.domain.Comment;
import com.community.entity.domain.Question;
import com.community.entity.enums.CommentTypeEnum;
import com.community.entity.vo.CommentVO;
import com.community.entity.vo.QuestionVO;
import com.community.service.ICommentService;
import com.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private ICommentService iCommentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {
        QuestionVO question = iQuestionService.getQuestionVOById(id);
        List<CommentVO> comments = iCommentService.getCommentVO(id, CommentTypeEnum.QUESTION.getType());
        question.setComments(comments);
        model.addAttribute("question", question);
        model.addAttribute("ralatedQuestion", "");
        return "question";
    }
}
