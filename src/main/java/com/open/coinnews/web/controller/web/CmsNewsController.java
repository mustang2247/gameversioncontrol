package com.open.coinnews.web.controller.web;

import com.open.coinnews.basic.auth.annotations.Token;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信推送
 */
@Controller
@RequestMapping
public class CmsNewsController {

    /** 初始化GET */
    @RequestMapping(value="cmsnews", method= RequestMethod.GET)
    @Token(flag= Token.READY)
    public String init(Model model, HttpServletRequest request) {
//        AppConfig appConfig = appConfigService.loadOne();
//        if(appConfig==null || appConfig.getInitFlag()==null || "0".equals(appConfig.getInitFlag())) {
//            //表示可以初始化
//            User user = new User();
//            user.setStatus(1);
//            model.addAttribute("user", user);
//            model.addAttribute("initFlag", true);
//        } else {
//            //表示不可以初始化
//            model.addAttribute("initFlag", false);
//        }
        return "index";
//        return "ws/snake";
    }

}
