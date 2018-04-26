package com.open.coinnews.basic.exception;

import com.open.coinnews.basic.exception.ErrorInfo;
import com.open.coinnews.basic.exception.SystemException;
import com.open.coinnews.basic.exception.WebException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SystemException.class)
    public String systemExceptionHandler(Model model, HttpServletRequest req, SystemException e) {
        ErrorInfo<String> er = new ErrorInfo<>();
        er.setCode(ErrorInfo.ERROR);
        er.setMessage(e.getMessage());
        er.setUrl(req.getRequestURL().toString());
        er.setParams(req.getQueryString());
        er.setDatas("发生异常，无法继续进行！");
        model.addAttribute("errorInfo", er);
//        e.printStackTrace();
        return "errors/system";
    }

    @ExceptionHandler(value = Exception.class)
    public String defaultExceptionHandler(Model model, HttpServletRequest req, Exception e) {
        ErrorInfo<String> er = new ErrorInfo<>();
        er.setCode(ErrorInfo.ERROR);
        er.setMessage(e.getMessage());
        er.setUrl(req.getRequestURL().toString());
        er.setParams(req.getQueryString());
        er.setDatas("发生异常，无法继续进行！");
        model.addAttribute("errorInfo", er);
//        e.printStackTrace();
        return "errors/default";
    }

    @ExceptionHandler(value = WebException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, WebException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setDatas("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
}
