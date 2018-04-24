package com.open.coinnews.web.controller.admin;

import com.open.coinnews.app.model.Category;
import com.open.coinnews.app.service.ICategoryService;
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
@RequestMapping(value = "admin/category")
@AdminAuth(name="分类管理", orderNum=2, psn="网站管理", pentity=0, porderNum=1)
public class AdminCategoryController {

    @Autowired
    private ICategoryService categoryService;

    /** 列表 */
    @AdminAuth(name = "分类列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Category> datas = categoryService.findAll(new ParamFilterTools<Category>().buildSpecification(model, request), PageableTools.basicPage(page, "asc", "orderNo"));
        model.addAttribute("datas", datas);
        return "admin/category/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name = "添加分类", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        Category category = new Category();
        category.setIsNav(0);
        model.addAttribute("category", category);
        return "admin/category/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, Category category, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            categoryService.save(category);
        }
        return "redirect:/admin/category/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改分类", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        model.addAttribute("category", categoryService.findOne(id));
        return "admin/category/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Category category, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Category c = categoryService.findOne(id);
            MyBeanUtils.copyProperties(category, c, new String[]{"id", "orderNo"});
            categoryService.save(c);
        }
        return "redirect:/admin/category/list";
    }

    @AdminAuth(name="删除分类", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable Integer id) {
        try {
            categoryService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    @RequestMapping("updateSort")
    @AdminAuth(name="分类排序", orderNum=4, type="2")
    public @ResponseBody String updateSort(Integer[] ids) {
        try {
            Integer index = 1;
            for(Integer id : ids) {
                categoryService.updateOrderNo(id, index++);
            }
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
}
