package com.community.controller;

import com.community.entity.domain.User;
import com.community.entity.vo.QuestionVO;
import com.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private IQuestionService iQuestionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          Model model){
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        User user = new User();
        List<QuestionVO> questions = iQuestionService.questionVOListByUserId(user.getId());
        model.addAttribute("questions",questions);
        return "profile";
    }
}
