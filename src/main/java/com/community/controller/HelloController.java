package com.community.controller;


import com.community.entity.vo.QuestionVO;
import com.community.service.IQuestionService;
import com.community.utils.JwtIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private IQuestionService iQuestionService;

    @JwtIgnore
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
    @JwtIgnore
    @GetMapping("/")
    public String index(Model model){
        List<QuestionVO> questions = iQuestionService.questionVOList();
        model.addAttribute("questions",questions);
        return "index";
    }
}
