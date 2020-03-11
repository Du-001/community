package com.community.entity.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    @TableId
    private Long id;
    private String accountId;
    private String name;
    private String token;
    @TableField(fill = FieldFill.INSERT)
    private LocalDate creatTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDate updateTime;
    @TableLogic
    private Integer deleted;
}
