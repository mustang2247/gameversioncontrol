package com.mybitop.gameversioncontrol.web.home;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.entity.Hotupdatecheckonline;
import com.mybitop.gameversioncontrol.entity.Hotupdateconfig;
import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;
import com.mybitop.gameversioncontrol.service.IHotUpdateCheckOnline;
import com.mybitop.gameversioncontrol.service.IHotUpdateCheck;
import com.mybitop.gameversioncontrol.service.IHotUpdateConfig;
import com.mybitop.gameversioncontrol.service.IHotUpdateNotice;
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
    private IHotUpdateCheck hotupdatecheck;
    /**
     * 配置文件
     */
    @Autowired
    IHotUpdateConfig versionConfig;

    @Autowired
    private IHotUpdateCheckOnline checkOnline;

    @Autowired
    private IHotUpdateNotice hotUpdateNotice;

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
        model.addAttribute("checkTitleOnline", "线上版本(需要同步才能使用)");
        model.addAttribute("confTitle", "热更新配置");
        List<Hotupdatecheck> checkList = hotupdatecheck.findAll();
        List<Hotupdateconfig> confList = versionConfig.findAll();
        List<Hotupdatecheckonline> checkOnlines = checkOnline.findAll();
        if(checkList != null){
            model.addAttribute("checkList", checkList);
        }else {
            model.addAttribute("checkList", null);
        }

        if(checkOnlines != null){
            model.addAttribute("checkListOnline", checkOnlines);
        }else {
            model.addAttribute("checkListOnline", null);
        }

        if(confList != null){
            model.addAttribute("confList", confList);
        }else {
            model.addAttribute("confList", null);
        }

        model.addAttribute("noticeInfo", "更新公告");
        List<Hotupdatenotice> noticeList = hotUpdateNotice.findAll();
        if(noticeList != null){
            model.addAttribute("noticeList", noticeList);
        }else {
            model.addAttribute("noticeList", null);
        }

        findHotupdatecheckonlineByPage(model, 1);
        findHotupdatecheckByPage(model, 1);
        findHotupdateconfigByPage(model, 1);
        return "home";
    }


    /**
     * 更新版本
     * */
    @RequestMapping(value = "/findHotupdatecheckonlineByPage", method = RequestMethod.POST)
    public String findHotupdatecheckonlineByPage(
            Model model, @RequestParam( value = "pageCount", required = false, defaultValue = "1") int pageCount
    ) {
        List<Hotupdatecheckonline> list = checkOnline.findHotupdatecheckonlineByPage((pageCount - 1) * 5);
        model.addAttribute("checkOnlinePageCount", (checkOnline.findAll().size() / 5) + 1);
        model.addAttribute("checkOnlineByPage", list);
        return "home";
    }

    @RequestMapping(value = "/findHotupdatecheckonlineByPageAjax", method = RequestMethod.POST)
    public @ResponseBody List findHotupdatecheckonlineByPageAjax(
            Model model, @RequestParam( value = "pageCount", required = false, defaultValue = "1") int pageCount
    ) {
        if( pageCount < 1 )
        {
            pageCount = 1;
        }else if( pageCount > (checkOnline.findAll().size() / 5) + 1)
        {
            pageCount = (checkOnline.findAll().size() / 5) + 1;
        }
        List<Hotupdatecheckonline> list = checkOnline.findHotupdatecheckonlineByPage((pageCount - 1) * 5);
        System.out.print(pageCount);
        return list;
    }


    /**
     * 部署版本
     * */
    @RequestMapping(value = "/findHotupdatecheckByPage", method = RequestMethod.POST)
    public String findHotupdatecheckByPage(
            Model model, @RequestParam( value = "pageCount", required = false, defaultValue = "1") int pageCount
    ) {
        List<Hotupdatecheck> list = hotupdatecheck.findHotupdatecheckByPage((pageCount - 1) * 5);
        model.addAttribute("hotupdatecheckPageCount", (hotupdatecheck.findAll().size() / 5) + 1);
        model.addAttribute("hotupdatecheckByPage", list);
        return "home";
    }

    @RequestMapping(value = "/findHotupdatecheckByPageAjax", method = RequestMethod.POST)
    public @ResponseBody List findHotupdatecheckByPageAjax(
            Model model, @RequestParam( value = "pageCount", required = false, defaultValue = "1") int pageCount
    ) {
        if( pageCount < 1 )
        {
            pageCount = 1;
        }else if( pageCount > (hotupdatecheck.findAll().size() / 5) + 1)
        {
            pageCount = (hotupdatecheck.findAll().size() / 5) + 1;
        }
        List<Hotupdatecheck> list = hotupdatecheck.findHotupdatecheckByPage((pageCount - 1) * 5);
        System.out.print(pageCount);
        return list;
    }

    /**
     * 热更新配置
     * */
    @RequestMapping(value = "/findHotupdateconfigByPage", method = RequestMethod.POST)
    public String findHotupdateconfigByPage(
            Model model, @RequestParam( value = "pageCount", required = false, defaultValue = "1") int pageCount
    ) {
        List<Hotupdateconfig> list = versionConfig.findHotupdateconfigByPageCount((pageCount - 1) * 5);
        model.addAttribute("hotupdateconfigPageCount", (versionConfig.findAll().size() / 5) + 1);
        model.addAttribute("hotupdateconfigByPage", list);
        return "home";
    }

    @RequestMapping(value = "/findHotupdateconfigByPageAjax", method = RequestMethod.POST)
    public @ResponseBody List findHotupdateconfigByPageAjax(
            Model model, @RequestParam( value = "pageCount", required = false, defaultValue = "1") int pageCount
    ) {
        if( pageCount < 1 )
        {
            pageCount = 1;
        }else if( pageCount > (versionConfig.findAll().size() / 5) + 1)
        {
            pageCount = (versionConfig.findAll().size() / 5) + 1;
        }
        List<Hotupdateconfig> list = versionConfig.findHotupdateconfigByPageCount((pageCount - 1) * 5);
        System.out.print(pageCount);
        return list;
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
