package com.community.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.domain.User;
import com.community.service.IUserService;
import com.community.utils.JwtIgnore;
import com.community.utils.JwtTokenUtil;
import com.community.utils.SpringContextHolder;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ========================
 * token验证拦截器
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/7/18 9:46
 * Version: v1.0
 * ========================
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IUserService iUserService;

    /**
     * header名称
     */
    @Value("jwt.tokenHeader")
    private String tokenHeader;

    /**
     * token前缀
     */
    @Value("jwt.tokenPrefix")
    private String tokenPrefix;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
//            if (jwtIgnore != null) {
//                return true;
//            }
//        }
//
//        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            return true;
//        }
//
//        // 获取请求头信息authorization信息
//        String authHeader = request.getHeader(tokenHeader);
//        System.out.println("## authHeader= {}"+authHeader);
//
//        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(tokenPrefix)) {
//            System.out.println("### 用户未登录，请先登录 ###");
//            throw new RuntimeException("用户未登录");
//        }
//
//        // 获取token
//        String token = authHeader.replace(tokenPrefix,"");
//        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
//        return jwtTokenUtil.isTokenExpired(token);

        if (iUserService == null) {
            iUserService = SpringContextHolder.getBean("iUserService");
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = iUserService.getOne(new QueryWrapper<User>().eq("token", token));
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return true;
    }

}

