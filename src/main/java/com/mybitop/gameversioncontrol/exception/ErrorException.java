package com.mybitop.gameversioncontrol.exception;

import com.mybitop.gameversioncontrol.enums.ResultEnum;

/**
 * 统一错误处理
 */
public class ErrorException extends RuntimeException {
    private Integer code;

    public ErrorException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
