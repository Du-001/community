package com.community.entity;


import com.community.entity.enums.ResultEnum;
import lombok.Data;

/**
 * @Author: Meiziyu
 * @Date: 2019/10/29 9:51
 * @Email 308348194@qq.com
 */
@Data
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;


    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        result.setData(data);
        return result;
    }

    public static Result success(Object data, ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(data);
        return result;
    }

    public static Result success(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        return result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static Result success(Object object,String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    /**
     * 错误返回
     *
     * @param resultEnum
     * @return
     */
    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }


    /**
     * 返回失败
     *
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(-1);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回失败
     *
     * @param msg
     * @return
     */
    public static Result error(String msg, int code) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
