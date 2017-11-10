package com.mybitop.gameversioncontrol.web.home;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request, Model model) {
        String name = "World";

        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();

        if (principalCollection != null && !principalCollection.isEmpty()) {
            Collection<Map> principalMaps = subject.getPrincipals().byType(Map.class);
            if (CollectionUtils.isEmpty(principalMaps)) {
                name = subject.getPrincipal().toString();
            }
            else {
                name = (String) principalMaps.iterator().next().get("username");
            }
        }

        model.addAttribute("name", name);

        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    // 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @ModelAttribute("shiroLoginFailure")String a, @RequestParam("username") String username) throws Exception {
        logger.debug("a" + a + "----");
        logger.debug("账号" + username + "登录失败");
        // 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request
                .getAttribute("shiroLoginFailure");
        logger.debug(exceptionClassName);

        String message = "登录失败！";
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(
                    exceptionClassName)) {
                message = "用户名/密码错误";
                logger.error("账号不存在----" + username);
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                message = "用户名/密码错误";
                logger.error("用户名/密码错误----" + username);
            } else if (ExcessiveAttemptsException.class.getName().equals(
                    exceptionClassName)) {
                message = "登录失败次数过多";
                logger.error("登录失败次数过多----" + username);
            } else if (LockedAccountException.class.getName().equals(
                    exceptionClassName)) {
                message = "账号已被锁定";
                logger.error("账号已被锁定----" + username);
            } else if (DisabledAccountException.class.getName().equals(
                    exceptionClassName)) {
                message = "账号已被禁用";
                logger.error("账号已被禁用----" + username);
            } else if (ExpiredCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                message = "账号已过期";
                logger.error("账号已过期----" + username);
            } else if (UnauthorizedException.class.getName().equals(
                    exceptionClassName)) {
                message = "账号没有相应的授权";
                logger.error("账号没有相应的授权----" + username);
            } else {
                message = "未知异常，请重试";
                logger.error("未知错误-----" + username);
            }
        }

        request.setAttribute("message",message);
        request.setAttribute("username",username);

        return "login";

//        System.out.println("HomeController.login()");
//        // 登录失败从request中获取shiro处理的异常信息。
//        // shiroLoginFailure:就是shiro异常类的全类名.
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//
//        System.out.println("exception=" + exception);
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                System.out.println("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                System.out.println("kaptchaValidateFailed -- > 验证码错误");
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else {
//                msg = "else >> " + exception;
//                System.out.println("else -- >" + exception);
//            }
//        }
//        map.put("msg", msg);
//        // 此方法不处理登录成功,由shiro进行处理.
//        return "user/login";
    }

    @ResponseBody
    @RequestMapping("unauthorized")
    public String aa(){
        return "unauthorized";
    }

}
