package com.open.coinnews.web.controller.admin;

import com.open.coinnews.app.model.Partner;
import com.open.coinnews.app.service.IPartnerService;
import com.open.coinnews.basic.auth.annotations.AdminAuth;
import com.open.coinnews.basic.auth.annotations.Token;
import com.open.coinnews.basic.auth.dto.AuthToken;
import com.open.coinnews.basic.auth.iservice.IRoleService;
import com.open.coinnews.basic.auth.iservice.IUserService;
import com.open.coinnews.basic.auth.model.Role;
import com.open.coinnews.basic.auth.model.User;
import com.open.coinnews.basic.auth.service.UserRoleServiceImpl;
import com.open.coinnews.basic.auth.tools.SecurityUtil;
import com.open.coinnews.basic.auth.tools.TokenTools;
import com.open.coinnews.basic.exception.SystemException;
import com.open.coinnews.basic.tools.MyBeanUtils;
import com.open.coinnews.basic.tools.PageableTools;
import com.open.coinnews.basic.tools.ParamFilterTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Controller
@RequestMapping(value = "admin/partner")
@AdminAuth(name = "团队管理", orderNum = 1, psn = "系统管理", pentity = 0, porderNum = 20)
public class AdminPartnerController {

    @Autowired
    private IPartnerService partnerService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    /**
     * 列表
     */
    @AdminAuth(name = "成员列表", orderNum = 1, icon = "icon-list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Partner> datas = partnerService.findAll(new ParamFilterTools<Partner>().buildSpecification(model, request), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/partner/list";
    }

    @Token(flag = Token.READY)
    @AdminAuth(name = "添加成员", orderNum = 2, icon = "icon-plus")
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        Partner partner = new Partner();
        partner.setStatus("1");
        model.addAttribute("partner", partner);
        return "admin/partner/add";
    }

    /**
     * 添加POST
     */
    @Token(flag = Token.CHECK)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, Partner partner, HttpServletRequest request) {
        if (TokenTools.isNoRepeat(request)) {
            Integer orderNo = partnerService.findMaxOrderNo();
            orderNo = orderNo == null || orderNo <= 0 ? 1 : orderNo + 1;
            partner.setCreateDate(new Date());
            partner.setOrderNo(orderNo);

            String username = partner.getUsername();
            if (username != null && !"".equalsIgnoreCase(username)) {
                User u = userService.findByUsername(username);
                if (u != null) {
                    throw new SystemException("用户名【" + username + "】已经存在！");
                } else {
                    u = new User();
                    try {
                        u.setPassword(SecurityUtil.md5(username, "blog_" + username));
                    } catch (NoSuchAlgorithmException e) {
//                        e.printStackTrace();
                    }
                    u.setCreateDate(new Date());
                    u.setNickname(partner.getRealName());
                    u.setStatus(1);
                    u.setUsername(username);
                    u.setIsAdmin(0);
                    userService.save(u);
                    partner.setUserId(u.getId());

                    //为用户分配角色
                    Role role = roleService.findBySn("ROLE_PARTNER");
                    if (role != null) {
                        userRoleServiceImpl.addOrDelete(u.getId(), role.getId());
                    }

                }
            }

            partnerService.save(partner);
        }
        return "redirect:/admin/partner/list";
    }

    @Token(flag = Token.READY)
    @AdminAuth(name = "修改成员", orderNum = 3, type = "2")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        model.addAttribute("partner", partnerService.findOne(id));
        return "admin/partner/update";
    }

    @Token(flag = Token.CHECK)
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Partner partner, HttpServletRequest request) {
        if (TokenTools.isNoRepeat(request)) {
            Partner p = partnerService.findOne(id);
            MyBeanUtils.copyProperties(partner, p, new String[]{"id", "userId", "username", "createDate"});

            partnerService.save(p);
        }
        return "redirect:/admin/partner/list";
    }

    @AdminAuth(name = "删除成员", orderNum = 4, type = "2")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable Integer id) {
        try {
            partnerService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    @RequestMapping("updateSort")
    @AdminAuth(name = "成员排序", orderNum = 4, type = "2")
    public @ResponseBody
    String updateSort(Integer[] ids) {
        try {
            int index = 1;
            for (Integer id : ids) {
                partnerService.updateOrderNo(index++, id);
            }
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }

    @RequestMapping(value = "updateOwn")
    public String updateOwn(Model model, Partner partner, HttpServletRequest request) {
        String method = request.getMethod();
        if (method.equalsIgnoreCase("get")) {
            AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);
            if (at != null) {
                model.addAttribute("partner", partnerService.findByUsername(at.getUser().getUsername()));
            }

            return "admin/partner/updateOwn";
        } else {
            AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);
            Partner p = partnerService.findByUsername(at.getUser().getUsername());
            if (p != null) {
                MyBeanUtils.copyProperties(partner, p, new String[]{"id", "status", "userId", "username", "createDate"});
                partnerService.save(p);
            }
            return "redirect:/admin/partner/updateOwn";
        }
    }
}
