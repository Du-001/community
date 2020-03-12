package com.community.controller;

import com.community.entity.domain.Question;
import com.community.entity.domain.User;
import com.community.service.IQuestionService;
import com.community.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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
        Cookie[] cookies = request.getCookies();
        Long userId = null;
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("Authorization")){
                String token = cookie.getValue();
                Claims claims = jwtTokenUtil.getClaimsFromToken(token);
//                userId= (Long) claims.get("userId");
            }
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        if(userId==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        question.setUserId(userId);
        iQuestionService.save(question);
        return "redirect:/";
    }
}
