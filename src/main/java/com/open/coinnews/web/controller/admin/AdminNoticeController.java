package com.open.coinnews.web.controller.admin;

import com.open.coinnews.app.model.Notice;
import com.open.coinnews.app.service.INoticeService;
import com.open.coinnews.basic.auth.annotations.AdminAuth;
import com.open.coinnews.basic.auth.annotations.Token;
import com.open.coinnews.basic.auth.tools.TokenTools;
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

@Controller
@RequestMapping(value = "admin/notice")
@AdminAuth(name="公告管理", orderNum=4, psn="网站管理", pentity=0, porderNum=1)
public class AdminNoticeController {

    @Autowired
    private INoticeService noticeService;

    /** 列表 */
    @AdminAuth(name = "公告列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Notice> datas = noticeService.findAll(new ParamFilterTools<Notice>().buildSpecification(model, request), PageableTools.basicPage(page, "asc", "orderNo"));
        model.addAttribute("datas", datas);
        return "admin/notice/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name = "添加公告", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        Notice notice = new Notice();
        notice.setIsShow(1);
        model.addAttribute("notice", notice);
        return "admin/notice/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, Notice notice, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Integer orderNo = noticeService.queryMaxOrderNo();
            orderNo = orderNo==null||orderNo<=0?1:orderNo+1;
            notice.setOrderNo(orderNo);
            noticeService.save(notice);
        }
        return "redirect:/admin/notice/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改公告", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        model.addAttribute("notice", noticeService.findOne(id));
        return "admin/notice/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Notice notice, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Notice n = noticeService.findOne(id);
            MyBeanUtils.copyProperties(notice, n, new String[]{"id", "orderNo", "createDate"});
            noticeService.save(n);
        }
        return "redirect:/admin/notice/list";
    }

    @AdminAuth(name="删除公告", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable Integer id) {
        try {
            noticeService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    @RequestMapping("updateSort")
    @AdminAuth(name="公告排序", orderNum=4, type="2")
    public @ResponseBody String updateSort(Integer[] ids) {
        try {
            Integer index = 1;
            for(Integer id : ids) {
                noticeService.updateOrderNo(id, index++);
            }
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
}
