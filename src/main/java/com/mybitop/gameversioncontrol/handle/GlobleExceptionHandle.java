package com.mybitop.gameversioncontrol.handle;

import com.mybitop.gameversioncontrol.entity.Result;
import com.mybitop.gameversioncontrol.enums.ResultEnum;
import com.mybitop.gameversioncontrol.exception.ErrorException;
import com.mybitop.gameversioncontrol.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobleExceptionHandle {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handle(Exception e) {

        ModelAndView mav = new ModelAndView();
        if (e instanceof ErrorException) {
            ErrorException errorException = (ErrorException) e;
            mav.addObject("errors", ResultUtil.error(errorException.getCode(), errorException.getMessage()));
        } else {
            mav.addObject("errors", ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg()));
        }

        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}