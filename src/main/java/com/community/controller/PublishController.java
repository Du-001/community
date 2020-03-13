package com.community.controller;

import com.community.entity.domain.Question;
import com.community.entity.domain.User;
import com.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private IQuestionService iQuestionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String addPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ) {
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        question.setUserId(user.getId());
        iQuestionService.save(question);
        return "redirect:/";
    }
}
