package com.community.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentDTO {
    private Long parentId;
    @NotEmpty(message = "评论内容不能为空")
    private String content;
    private Integer type;
}
