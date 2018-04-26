package com.open.coinnews.web.controller.admin;

import com.open.coinnews.app.model.Comment;
import com.open.coinnews.app.service.IArticleService;
import com.open.coinnews.app.service.ICommentService;
import com.open.coinnews.basic.auth.annotations.AdminAuth;
import com.open.coinnews.basic.auth.annotations.Token;
import com.open.coinnews.basic.auth.dto.AuthToken;
import com.open.coinnews.basic.auth.tools.TokenTools;
import com.open.coinnews.basic.exception.SystemException;
import com.open.coinnews.basic.tools.BaseSpecification;
import com.open.coinnews.basic.tools.PageableTools;
import com.open.coinnews.basic.tools.ParamFilterTools;
import com.open.coinnews.basic.tools.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "admin/comment")
@AdminAuth(name="点评管理", orderNum=7, psn="网站管理", pentity=0, porderNum=1)
public class AdminCommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IArticleService articleService;

    /** 列表 */
    @AdminAuth(name = "点评列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);
        Specifications<Comment> spes = null;
        if(at.getUser().getIsAdmin()==null || at.getUser().getIsAdmin()!=1){
            spes = Specifications.where(new BaseSpecification<>(new SearchCriteria("userId", "eq", at.getUser().getId())));
        }

        Page<Comment> datas = commentService.findAll(new ParamFilterTools<Comment>().buildSpecification(model, request, spes), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/comment/list";
    }

    @Token(flag= Token.READY)
    @AdminAuth(name="回复点评", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {

        Comment comment = commentService.findOne(id);

        AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);
        if(at!=null && (at.getUser().getIsAdmin() ==null || at.getUser().getIsAdmin()!=1)) {
            if(comment.getUserId()==null || comment.getUserId()!=at.getUser().getId()) {
                throw new SystemException("不是您管辖范围内的点评不可修改！");
            }
        }

        model.addAttribute("comment", comment);
        return "admin/comment/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Comment comment, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Comment c = commentService.findOne(id);
            c.setContent(comment.getContent());
            c.setIsShow(comment.getIsShow());
            c.setReply(comment.getReply());
            c.setReplyDate(new Date());
            commentService.save(c);
        }
        return "redirect:/admin/comment/list";
    }

    @AdminAuth(name="删除点评", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}/{artId}", method= RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable Integer id, @PathVariable Integer artId) {
        try {
            commentService.delete(id);
            articleService.updateCommentCount(artId, -1); //修改
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }
}
