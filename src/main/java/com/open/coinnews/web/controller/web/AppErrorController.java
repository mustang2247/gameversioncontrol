package com.open.coinnews.web.controller.web;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = "/404")
    public String e404(Model model, HttpServletRequest request) {
        return "404";
    }

    @RequestMapping(value = "/500")
    public String e500(Model model, HttpServletRequest request) {
        return "/500.html";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
