package com.open.coinnews.web.controller.basic;

import com.open.coinnews.basic.auth.dto.AuthToken;
import com.open.coinnews.basic.auth.iservice.IUserService;
import com.open.coinnews.basic.auth.model.User;
import com.open.coinnews.basic.auth.tools.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    private IUserService userService;

    /** 后台首页 */
    @RequestMapping(value={"", "/"}, method= RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        return "admin/index";
    }

    /** 修改密码 */
    @RequestMapping(value="updatePwd")
    public String updatePwd(Model model, Integer flag, String oldPwd, String password, String nickname, HttpServletRequest request) {
        String method = request.getMethod(); //获取请求方式，GET、POST
        if("get".equalsIgnoreCase(method)) {
            model.addAttribute("flag", flag);
            return "admin/updatePwd";
        } else if("post".equalsIgnoreCase(method)) {
            AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);
            User user = at.getUser();
            try {
                if(password!=null && !"".equals(password)) { //如果没有输入密码，则不修改
                    if(!SecurityUtil.md5(user.getUsername(), oldPwd).equals(user.getPassword())) {
                        model.addAttribute("errorMsg", "原始密码输入错误");
                        return "admin/updatePwd";
                    }
                    user.setPassword(SecurityUtil.md5(user.getUsername(), password));
                }
                user.setNickname(nickname);
                userService.save(user);
                return "redirect:/admin/updatePwd?flag=1";
            } catch (Exception e) {
                //e.printStackTrace();
                return "redirect:/admin/updatePwd?flag=2";
            }
        }
        return "redirect:/admin/updatePwd";
    }
}
