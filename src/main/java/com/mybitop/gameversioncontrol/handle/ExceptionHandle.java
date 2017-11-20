package com.mybitop.gameversioncontrol.handle;

import com.mybitop.gameversioncontrol.entity.Result;
import com.mybitop.gameversioncontrol.enums.ResultEnum;
import com.mybitop.gameversioncontrol.exception.ErrorException;
import com.mybitop.gameversioncontrol.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof ErrorException) {
            ErrorException girlExpection = (ErrorException) e;
            return ResultUtil.error(girlExpection.getCode(), girlExpection.getMessage());
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }
}