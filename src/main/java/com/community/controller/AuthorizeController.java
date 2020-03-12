package com.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.Result;
import com.community.entity.domain.User;
import com.community.entity.dto.AccessTokenDTO;
import com.community.entity.dto.GitHubUser;
import com.community.mapper.UserMapper;
import com.community.service.IUserService;
import com.community.utils.JwtIgnore;
import com.community.utils.JwtTokenUtil;
import com.community.utils.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider githubProvider;
    @Autowired
    IUserService iUserService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;


    @JwtIgnore
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println("accessToken="+accessToken);
        GitHubUser gitHubUser = githubProvider.gitHubUser(accessToken);
        if (gitHubUser == null || gitHubUser.getId()==null) {
//            return Result.error("登录失败");
            return "redirect:/";
        }
        User user = iUserService.getOne(new QueryWrapper<User>().eq("account_id", gitHubUser.getId()));
        if (user == null) {
            user = new User();
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            user.setName(gitHubUser.getName());
            iUserService.save(user);
        }
        String token = jwtTokenUtil.createToken(user.getId(), user.getName(), "user");
        System.out.println("token:"+token);
        request.getSession().setAttribute("user",user);
        System.out.println("登录成功");
        response.addCookie(new Cookie("token",token));
//        return Result.success(token);
        return "redirect:/";
    }
}
