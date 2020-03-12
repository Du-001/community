package com.community.entity.enums;

import lombok.Getter;

/**
 * @Author: Meiziyu
 * @Date: 2019/10/29 9:52
 * @Email 308348194@qq.com
 */
@Getter
public enum ResultEnum {

    ERROR(-1, "操作失败"),

    OK(1, "请求成功"),

    INSERT_OK(1, "添加成功"),

    INSERT_FAIL(-1, "添加失败"),

    QUERY_OK(1, "查找成功"),

    QUERY_FAIL(-1, "查找失败"),

    DELETE_OK(1, "删除成功"),

    DELETE_FAIL(-1, "删除失败"),

    UPDATE_OK(1, "修改成功"),

    UPDATE_FAIL(-1, "修改失败"),

    TOKEN_NOT_VALID(1000, "token不合法"),

    ACCESS_NOT(1001, "权限不足"),

    TOKEN_IS_NOT_VALID(1002, "token无效,请重新登录"),

    INVALID_PARAMETER(1003,"参数有错"),

    USER_NOT_FOUND(1004, "用户信息不存在"),

    ERROR_PASSWORD(1005, "密码错误"),

    USER_OR_PASSWORD_ERROR(1004, "请输入正确的账号或密码"),

    SYSTEM_ERROR(1004, "登录系统不存在"),

    HEADER_ERROR(1004, "header信息有误"),

    BLACK_LIST(1007, "黑名单token,请重新登录或更换账号"),

    EXPIRE_REFRESH(1008, "token过期,可以刷新"),

    EXPIRE_LOGIN(1009,"token过期,请重新登录"),

    REPATED_REG(1010,"重复注册"),

    ERROR_PHONENUMBER(-1,"不正确的手机号"),

    SERVER_ERROR(2000,"服务异常");






    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
