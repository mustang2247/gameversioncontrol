package com.mybitop.gameversioncontrol.web.home;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.entity.Versioncontrol;
import com.mybitop.gameversioncontrol.service.IHotupdatecheck;
import com.mybitop.gameversioncontrol.service.IVersioncontrol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * 检查热更新
     */
    @Autowired
    private IHotupdatecheck hotupdatecheck;
    /**
     * 配置文件
     */
    @Autowired
    IVersioncontrol versionConfig;

    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request, Model model) {
        String name = "World";

//        Subject subject = SecurityUtils.getSubject();
//
//        PrincipalCollection principalCollection = subject.getPrincipals();
//
//        if (principalCollection != null && !principalCollection.isEmpty()) {
//            Collection<Map> principalMaps = subject.getPrincipals().byType(Map.class);
//            if (CollectionUtils.isEmpty(principalMaps)) {
//                name = subject.getPrincipal().toString();
//            }
//            else {
//                name = (String) principalMaps.iterator().next().get("username");
//            }
//        }

        model.addAttribute("checkTitle", "部署版本");
        model.addAttribute("confTitle", "热更新配置");
        List<Hotupdatecheck> checkList = hotupdatecheck.select();
        List<Versioncontrol> confList = versionConfig.select();
        if(checkList != null){
            model.addAttribute("checkList", checkList);
        }else {
            model.addAttribute("checkList", null);
        }

        if(confList != null){
            model.addAttribute("confList", confList);
        }else {
            model.addAttribute("confList", null);
        }

        return "home";
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

//        String message = "登录失败！";
//        if (exceptionClassName != null) {
//            if (UnknownAccountException.class.getName().equals(
//                    exceptionClassName)) {
//                message = "用户名/密码错误";
//                logger.error("账号不存在----" + username);
//            } else if (IncorrectCredentialsException.class.getName().equals(
//                    exceptionClassName)) {
//                message = "用户名/密码错误";
//                logger.error("用户名/密码错误----" + username);
//            } else if (ExcessiveAttemptsException.class.getName().equals(
//                    exceptionClassName)) {
//                message = "登录失败次数过多";
//                logger.error("登录失败次数过多----" + username);
//            } else if (LockedAccountException.class.getName().equals(
//                    exceptionClassName)) {
//                message = "账号已被锁定";
//                logger.error("账号已被锁定----" + username);
//            } else if (DisabledAccountException.class.getName().equals(
//                    exceptionClassName)) {
//                message = "账号已被禁用";
//                logger.error("账号已被禁用----" + username);
//            } else if (ExpiredCredentialsException.class.getName().equals(
//                    exceptionClassName)) {
//                message = "账号已过期";
//                logger.error("账号已过期----" + username);
//            } else if (UnauthorizedException.class.getName().equals(
//                    exceptionClassName)) {
//                message = "账号没有相应的授权";
//                logger.error("账号没有相应的授权----" + username);
//            } else {
//                message = "未知异常，请重试";
//                logger.error("未知错误-----" + username);
//            }
//        }

//        request.setAttribute("message",message);
        request.setAttribute("username",username);

        return "login";
    }

    @ResponseBody
    @RequestMapping("unauthorized")
    public String aa(){
        return "unauthorized";
    }

}
