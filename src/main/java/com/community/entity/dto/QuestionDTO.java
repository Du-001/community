package com.community.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class QuestionDTO {
    private Long id;
    @Size(min=2, max=25)
    @NotNull
    private String title;
    @NotEmpty(message = "描述不能为空")
    private String description;
    @NotEmpty(message = "标签不能为空")
    private String tag;
}
