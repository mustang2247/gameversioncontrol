package com.open.coinnews.web.controller.web;

import com.open.coinnews.app.model.Comment;
import com.open.coinnews.app.model.News;
import com.open.coinnews.app.service.ICommentService;
import com.open.coinnews.app.service.INewsService;
import com.open.coinnews.app.service.IPartnerService;
import com.open.coinnews.basic.tools.BaseSpecification;
import com.open.coinnews.basic.tools.PageableTools;
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
@RequestMapping(value = "web/news")
public class WebNewsController {

    @Autowired
    private INewsService articleService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IPartnerService partnerService;

    /** 文章详情 */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable Integer id, HttpServletRequest request) {
        articleService.updateReadCount(id); //修改浏览量

        News article = articleService.findOne(id);
        model.addAttribute("article", article); //获取文章信息
//        model.addAttribute("nextArt", queryOne(article.getCateId(), id, true));
//        model.addAttribute("preArt", queryOne(article.getCateId(), id, false));

        Page<Comment> comments = commentService.findAll(id, PageableTools.basicPage(0, 30));
        model.addAttribute("comments", comments);

        if(article.getUserId()!=null && article.getUserId()>0) {
            model.addAttribute("partner", partnerService.findByUserId(article.getUserId()));
        }

        return "web/article/detail";
    }

    private News queryOne(Integer cateId, Integer id, boolean next) {
        String opt = next?"gt":"lt";
        Specifications<News> infoParam = Specifications.where(new BaseSpecification<News>(new SearchCriteria("cateId", "eq", cateId)));
        infoParam = infoParam.and(new BaseSpecification<>(new SearchCriteria("id", opt, id)));

        Page<News> artList = articleService.findAll(infoParam, PageableTools.basicPage(0,1));
        return artList.getTotalElements()>=1?artList.iterator().next():null;
    }

    private boolean isNull(String field) {
        return (field==null || "".equalsIgnoreCase(field.trim()));
    }

    /** 点评文章 */
    @RequestMapping(value="addComment", method= RequestMethod.POST)
    public @ResponseBody String addComment(Integer artId, String artTitle, String content, HttpServletRequest request) {
        if(artId==null || artId<=0 || isNull(artTitle) || isNull(content)) {return "params is error";}
        try {
            Integer userId = articleService.queryUserId(artId);
            Comment comment = new Comment();
            comment.setIsShow(1);
            comment.setContent(content);
            comment.setArtId(artId);
            comment.setArtTitle(artTitle);
            comment.setCreateDate(new Date());
            comment.setUserId(userId);
            comment.setIp(request.getRemoteAddr());
            commentService.save(comment);

            articleService.updateCommentCount(artId, 1); //修改文章的点评数量

            return "1";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
