package com.community.exception.advice;

import com.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {

    //未处理"application/json"格式的异常
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable ex, Model model) {
        if (ex instanceof CustomizeException) {
            model.addAttribute("message", ex.getMessage());
        } else {
            model.addAttribute("message", "服务找不到了，一会再试试呗。。。。");
        }
        return new ModelAndView("error");
    }
}
