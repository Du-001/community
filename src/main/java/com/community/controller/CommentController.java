package com.community.controller;


import com.community.entity.Result;
import com.community.entity.domain.Comment;
import com.community.entity.domain.User;
import com.community.entity.dto.CommentDTO;
import com.community.entity.enums.CommentTypeEnum;
import com.community.entity.enums.ResultEnum;
import com.community.entity.vo.CommentVO;
import com.community.service.ICommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        if (commentDTO == null || StringUtils.isEmpty(commentDTO.getContent())) {
            return Result.error("回复内容不能为空");
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setCommentator(user.getId());
        return iCommentService.saveComment(comment);
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public Result getSubComment(@PathVariable(name = "id")Long id){
        List<CommentVO> commentVO = iCommentService.getCommentVO(id, CommentTypeEnum.COMMENT.getType());
        return Result.success(commentVO);
    }
}

