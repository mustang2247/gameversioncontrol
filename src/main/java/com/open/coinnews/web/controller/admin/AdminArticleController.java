package com.open.coinnews.web.controller.admin;

import com.open.coinnews.app.model.Article;
import com.open.coinnews.app.model.Tag;
import com.open.coinnews.app.service.IArticleService;
import com.open.coinnews.app.service.ICategoryService;
import com.open.coinnews.app.service.ITagService;
import com.open.coinnews.app.tools.HtmlRegexpTools;
import com.open.coinnews.basic.auth.annotations.AdminAuth;
import com.open.coinnews.basic.auth.annotations.Token;
import com.open.coinnews.basic.auth.dto.AuthToken;
import com.open.coinnews.basic.auth.tools.TokenTools;
import com.open.coinnews.basic.exception.SystemException;
import com.open.coinnews.basic.tools.*;
import com.open.coinnews.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "admin/article")
@AdminAuth(name="文章管理", orderNum=1, psn="网站管理", pentity=0, porderNum=1)
public class AdminArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private ConfigTools configTools;

    private static final String PATH_PRE = Utils.BASE_PATH_PRE + "article/";

    /** 列表 */
    @AdminAuth(name = "文章列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);
        Specifications<Article> spes = null;
        if(at.getUser().getIsAdmin()==null || at.getUser().getIsAdmin()!=1){
            spes = Specifications.where(new BaseSpecification<>(new SearchCriteria("userId", "eq", at.getUser().getId())));
        }
        Page<Article> datas = articleService.findAll(new ParamFilterTools<Article>().buildSpecification(model, request, spes), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/article/list";
    }

    /** 校验文章标题 */
    @RequestMapping(value="/checkTitle", method= RequestMethod.GET)
    @ResponseBody
    public String checkTitle( @RequestParam String title) {
        List<Article> articles = articleService.queryArticleTitle(title);
        String msg = null;
        if (articles.size()>0){
            msg = "NONULL";
        }
        return msg;
    }

    @Token(flag=Token.READY)
    @AdminAuth(name = "添加文章", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        Article article = new Article();
        article.setIsShow(1);
        model.addAttribute("article", article);
        model.addAttribute("cateList", categoryService.findAll());
        return "admin/article/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, Article article, HttpServletRequest request, @RequestParam("file")MultipartFile[] files) {
        if(TokenTools.isNoRepeat(request)) {
            if(article.getGuide()==null || "".equals(article.getGuide().trim())) {
                String guide = HtmlRegexpTools.filterHtml(article.getContent()).replace("[TOCM]","").replace("[TOC]", "");
                if(guide.length()>200) {guide = guide.substring(0, 200) + "……";}
                article.setGuide(guide);
            }
            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                        File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                        article.setPicPath(configTools.getFileBaseUrl() + outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                        FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(bw!=null) {bw.close();}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);
            if(at!=null) {
                article.setUserId(at.getUser().getId());
                article.setRealName(at.getUser().getNickname());
            }

            article.setReadCount(0);
            article.setCommentCount(0);
            article.setCreateDate(new Date());
            addOrUpdateTags(article.getTags());
            articleService.save(article);
        }
        return "redirect:/admin/article/list";
    }

    private void addOrUpdateTags(String tags) {
        String [] tagArray = tags.split(",");
        for(String tag : tagArray) {
            if(tag==null || "".equals(tag.trim())) {continue;}
            Tag t = tagService.findByName(tag);
            if(t==null) {
                t = new Tag();
                t.setName(tag);
                tagService.save(t);
            }
        }
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改文章", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        Article article = articleService.findOne(id);

        AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);
        if(at!=null && (at.getUser().getIsAdmin() ==null || at.getUser().getIsAdmin()!=1)) {
            if(article.getUserId()==null || article.getUserId()!=at.getUser().getId()) {
                throw new SystemException("不是您管辖范围内的文章不可修改！");
            }
        }

        model.addAttribute("article", article);
        model.addAttribute("cateList", categoryService.findAll());

        return "admin/article/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Article article, HttpServletRequest request, @RequestParam("file")MultipartFile[] files) {
        if(TokenTools.isNoRepeat(request)) {
            Article art = articleService.findOne(id);
            MyBeanUtils.copyProperties(article, art, new String[]{"id", "userId", "realName", "createDate"});
            if(art.getGuide()==null || "".equals(art.getGuide().trim())) {
                String guide = HtmlRegexpTools.filterHtml(art.getContent()).replace("[TOCM]","").replace("[TOC]", "");
                if(guide.length()>200) {guide = guide.substring(0, 200) + "……";}
                guide = guide.replaceAll("\n", "&nbsp;&nbsp;");
                art.setGuide(guide);
            }

            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {

                        File oldFile = new File(configTools.getUploadPath()+art.getPicPath());
                        if(oldFile.exists()) {oldFile.delete();}

                        File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                        art.setPicPath(configTools.getFileBaseUrl() + outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                        FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(bw!=null) {bw.close();}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            articleService.save(art);
            addOrUpdateTags(art.getTags());
        }
        return "redirect:/admin/article/list";
    }

    @AdminAuth(name="删除文章", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable Integer id) {
        try {
            articleService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }
}
