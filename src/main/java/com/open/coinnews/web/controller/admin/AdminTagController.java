package com.open.coinnews.web.controller.admin;

import com.open.coinnews.app.model.Tag;
import com.open.coinnews.app.service.ITagService;
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
@RequestMapping(value = "admin/tag")
@AdminAuth(name="标签管理", orderNum=3, psn="网站管理", pentity=0, porderNum=1)
public class AdminTagController {

    @Autowired
    private ITagService tagService;

    /** 列表 */
    @AdminAuth(name = "标签列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Tag> datas = tagService.findAll(new ParamFilterTools<Tag>().buildSpecification(model, request), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/tag/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改标签", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        model.addAttribute("tag", tagService.findOne(id));
        return "admin/tag/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Tag tag, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Tag t = tagService.findOne(id);
            MyBeanUtils.copyProperties(tag, t, new String[]{"id"});
            tagService.save(t);
        }
        return "redirect:/admin/tag/list";
    }

    @AdminAuth(name="删除标签", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable Integer id) {
        try {
            tagService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }
}
