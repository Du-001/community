package com.community.controller;

import com.community.entity.domain.Question;
import com.community.entity.domain.Tag;
import com.community.entity.domain.User;
import com.community.entity.dto.QuestionDTO;
import com.community.exception.CustomizeException;
import com.community.exception.emuns.CustomizeErrorCode;
import com.community.service.IQuestionService;
import com.community.service.ITagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private ITagService iTagService;

    @GetMapping("/publish/{questionId}")
    public String edit(@PathVariable("questionId") Long questionId,
                       Model model) {
        if (questionId != null) {
            QuestionDTO questionDTO = iQuestionService.getQuestionDTOById(questionId);
            model.addAttribute("questionDTO", questionDTO);
            return "publish";
        }else {
            throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
    }

    @GetMapping("/publish")
    public String publish(HttpServletRequest request,Model model,QuestionDTO questionDTO) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            throw  new CustomizeException(CustomizeErrorCode.USER_NOT_LOGIN);
        }
        List<Tag> tag = iTagService.getTag();
        model.addAttribute("tags",tag);
        return "publish";
    }

    @PostMapping("/publish")
    public String addPublish(
            @Validated QuestionDTO questionDTO,
            BindingResult bindingResult,
            HttpServletRequest request,
            Model model
    ) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "redirect:/publish";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "填写错误");
            return "redirect:/publish";
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question);
        question.setUserId(user.getId());
        iQuestionService.saveOrUpdate(question);
        return "redirect:/";
    }
}
