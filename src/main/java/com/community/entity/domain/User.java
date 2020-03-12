package com.community.entity.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.community.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author du
 * @since 2020-03-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user")
@Accessors(chain = true)
public class User extends BaseEntity {

    /**
     * 第三方登录
     */
    private String accountId;

    /**
     * 名字
     */
    private String name;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 登录状态
     */
    private String token;

}
