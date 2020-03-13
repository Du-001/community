package com.community.controller;

import com.community.entity.vo.QuestionVO;
import com.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private IQuestionService iQuestionService;
    
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        QuestionVO question = iQuestionService.getQuestionVOById(id);
        model.addAttribute("question",question);
        return "question";
    }
}
