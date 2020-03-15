package com.community.controller;


import com.community.entity.Result;
import com.community.entity.domain.Comment;
import com.community.entity.domain.User;
import com.community.entity.dto.CommentDTO;
import com.community.entity.enums.ResultEnum;
import com.community.exception.CustomizeException;
import com.community.exception.emuns.CustomizeErrorCode;
import com.community.exception.emuns.ICustomizeErrorCode;
import com.community.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author du
 * @since 2020-03-15
 */
@RestController
public class CommentController {

    @Autowired
    private ICommentService iCommentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Result post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.error(ResultEnum.TOKEN_IS_NOT_VALID);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setCommentator(user.getId());
        return iCommentService.saveComment(comment);
    }
}

