package com.open.coinnews.web.controller.web;

import com.alibaba.fastjson.JSON;
import com.open.coinnews.app.dto.CateDto;
import com.open.coinnews.app.model.*;
import com.open.coinnews.app.service.*;
import com.open.coinnews.app.tools.HtmlRegexpTools;
import com.open.coinnews.basic.auth.dto.AuthToken;
import com.open.coinnews.basic.tools.*;
import com.open.coinnews.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 公共的Controller
 */
@RestController
@RequestMapping(value = "public")
public class PublicController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    public ICommentService commentService;

    @Autowired
    private INewsService newsService;

    @Autowired
    private ConfigTools configTools;

    @Autowired
    private ITopPicService topPicService;

    @Autowired
    private IVideoService videoService;

    private static final String PATH_PRE = Utils.BASE_PATH_PRE + "public/";

    /**
     * 获取标签
     */
    @RequestMapping(value = "listTags", method = RequestMethod.GET)
    public List<Tag> listTags() {
        return tagService.findAll();
    }

    /**
     * 栏目列表
     */
    @RequestMapping(value = "listCates", method = RequestMethod.GET)
    public List<CateDto> listCates() {
        List<CateDto> cateDtoList = articleService.queryCates();
        return cateDtoList;
    }

    /**
     * 获取访问最最高的几条文章
     */
    @RequestMapping(value = "hotArt", method = RequestMethod.GET)
    public Page<Article> hotArt(Integer length) {
        length = (length == null || length <= 0) ? 10 : length; //默认为10

        Specifications<Article> spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("isShow", BaseSpecification.EQUAL, 1)));
        Page<Article> artList = articleService.findAll(spe, PageableTools.basicPage(0, length, "desc", "readCount"));
        return artList;
    }

    /**
     * 最新文章-获取最新发布的几条文章
     */
    @RequestMapping(value = "newArt", method = RequestMethod.GET)
    public Page<Article> newArt(Integer length) {
        length = (length == null || length <= 0) ? 10 : length; //默认为10

        Specifications<Article> spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("isShow", BaseSpecification.EQUAL, 1)));
        Page<Article> artList = articleService.findAll(spe, PageableTools.basicPage(0, length, "desc", "createDate"));
        return artList;
    }

    /**
     * 获取公告信息
     */
    @RequestMapping(value = "listNotice", method = RequestMethod.GET)
    public List<Notice> listNotice() {
        Specifications<Notice> pars = Specifications.where(new BaseSpecification<Notice>(new SearchCriteria("isShow", "eq", 1)));
        List<Notice> res = noticeService.findAll(pars, SortTools.basicSort("asc", "orderNo"));
        return res;
    }

    /**
     * 显示的文章条数
     */
    @RequestMapping(value = "articleCount", method = RequestMethod.GET)
    public Long articleCount() {
        return articleService.queryCount();
    }

    /**
     * 文章阅读总数
     */
    @RequestMapping(value = "articleReadCount", method = RequestMethod.GET)
    public Long articleReadCount() {
        long count = articleService.queryReadCount();
        return count;
    }

    /**
     * 获取最新的几条点评
     */
    @RequestMapping(value = "newComment", method = RequestMethod.GET)
    public Page<Comment> newComment(Integer length) {
        length = (length == null || length <= 0) ? 10 : length; //默认为10
        Page<Comment> commentList = commentService.findAll(PageableTools.basicPage(0, length, "desc", "createDate"));
        return commentList;
    }

    /**
     * 上传demo
     * success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
     * message : "提示的信息，上传成功或上传失败及错误信息等。",
     * url     : "图片地址"        // 上传成功时才返回
     */
    @RequestMapping("upload")
    public @ResponseBody
    Map<String, Object> upload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile files, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (files != null) {
            BufferedOutputStream bw = null;
            try {
                String fileName = files.getOriginalFilename();
                if (fileName != null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                    File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString() + NormalTools.getFileType(fileName));

                    String filePath = outFile.getAbsolutePath().replace(configTools.getUploadPath(), "");
                    resultMap.put("success", 1);
                    resultMap.put("message", "上传成功！");
                    resultMap.put("url", configTools.getFileBaseUrl() + filePath);

                    FileUtils.copyInputStreamToFile(files.getInputStream(), outFile);
                }
            } catch (IOException e) {
                resultMap.put("success", 0);
                resultMap.put("message", "上传失败！");
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultMap;
    }

    //=================

    @RequestMapping("uploadCkeditor")
    public @ResponseBody
    void uploadCkeditor(@RequestParam(value = "upload", required = false) MultipartFile files, HttpServletRequest request,HttpServletResponse response) throws IOException {

        if (files != null) {
            BufferedOutputStream bw = null;
            try {

                response.setContentType("text/html; charset=UTF-8");
                response.setHeader("Cache-Control", "no-cache");

                PrintWriter out = response.getWriter();

                String fileName = files.getOriginalFilename();
                if (fileName != null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                    File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString() + NormalTools.getFileType(fileName));

                    String filePath = outFile.getAbsolutePath().replace(configTools.getUploadPath(), "");
                    filePath.replace("-","");
                    String fileUrl = configTools.getFileBaseUrl()+ filePath;

                    fileUrl = fileUrl.replaceAll("\\\\", "/");
                    String callback = request.getParameter("CKEditorFuncNum");
                    String script = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + callback + ", '" + fileUrl + "');</script>";
                    out.println(script);
                    out.flush();
                    out.close();

                    FileUtils.copyInputStreamToFile(files.getInputStream(), outFile);
                }
            } catch (IOException e) {

                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    //=================

    /**
     * 获取分类列表
     */
    @RequestMapping(value = "listCategories", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> listCategories() {
        return categoryService.findAll(SortTools.basicSort("asc", "orderNo"));
    }

    /**
     * 获取当天的快讯文章
     */
    @RequestMapping(value = "newsList/{page}", method = RequestMethod.GET)
    @ResponseBody
    public Page<News> newsList(@PathVariable(value = "page") Integer page) {

        Specifications<News> spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("isShow", BaseSpecification.EQUAL, 1)));
        return newsService.findAll(spe, PageableTools.basicPage(page, "desc", "createDate"));
    }

    /**
     * 获取文章
     */
    @RequestMapping(value = "articleList/{cateId}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public Page<Article> articleList(@PathVariable(value = "cateId") Integer cateId, @PathVariable(value = "page") Integer page) {
        Specifications<Article> spe = null;
        if (cateId != null && cateId > 0) {
            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("cateId", "eq", cateId)));
        }

        if (spe == null) {
            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("isShow", BaseSpecification.EQUAL, 1)));
        }
        Page<Article> datas = articleService.findAll(spe, PageableTools.basicPage(page, "desc", "createDate"));
        return datas;
    }

    /**
     * 文章详情
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Article detail(@PathVariable(value = "id") Integer id) {
        articleService.updateReadCount(id); //修改浏览量

        Article article = articleService.findOne(id);
        return article;
    }

    /**
     * 页头动
     *
     * @return
     */
    @RequestMapping("toppic")
    @ResponseBody
    public List<TopPic> topPicList() {
        List<TopPic> pics = topPicService.findAll(Specifications.where(new BaseSpecification<>(new SearchCriteria("status", "eq", "1"))), SortTools.basicSort("asc", "orderNo"));
        return pics;
    }

    /**
     * 添加文章公共接口POST
     */
    @RequestMapping(value = "addArticleByWechatSpider", method = RequestMethod.POST)
    @ResponseBody
    public String addArticleByWechatSpider(String data, HttpServletRequest request) {
        String result = "-1";
        try {
            Article article = JSON.parseObject(data, Article.class);
            if (article.getGuide() == null || "".equals(article.getGuide().trim())) {
                String guide = HtmlRegexpTools.filterHtml(article.getContent()).replace("[TOCM]", "").replace("[TOC]", "");
                if (guide.length() > 200) {
                    guide = guide.substring(0, 200) + "……";
                }
                article.setGuide(guide);
            }

            AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);
            if (at != null) {
                article.setUserId(at.getUser().getId());
                article.setRealName(at.getUser().getNickname());
            }

            article.setReadCount(0);
            article.setCommentCount(0);
            article.setCreateDate(new Date());
            addOrUpdateTags(article.getTags());
            articleService.save(article);

            result = "1";

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    private void addOrUpdateTags(String tags) {
        String[] tagArray = tags.split(",");
        for (String tag : tagArray) {
            if (tag == null || "".equals(tag.trim())) {
                continue;
            }
            Tag t = tagService.findByName(tag);
            if (t == null) {
                t = new Tag();
                t.setName(tag);
                tagService.save(t);
            }
        }
    }

    /**
     * 添加快讯公共接口POST
     */
    @RequestMapping(value = "addNewsByWechatSpider", method = RequestMethod.POST)
    @ResponseBody
    public String addNewsByWechatSpider(String data, HttpServletRequest request) {
        String result = "-1";
        try {
//            System.out.println(data);
            List<News> newsList = JSON.parseArray(data, News.class);

            if (newsList != null){
//                for (int i = 0; i < newsList.size(); i++ ){
//                    newsService.save(newsList.get(i));
//                }

                for(Iterator<News> it = newsList.iterator();it.hasNext();){
                    News news = it.next();
                    if (news != null && news.getContent().contains("币世界")){
                        it.remove();
                    }
                }

                newsService.save(newsList);
            }

            result = "1";

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }


    /**
     * 获取视频
     */
    @RequestMapping(value = "videoList/{cateId}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public Page<Video> videoList(@PathVariable(value = "cateId") Integer cateId, @PathVariable(value = "page") Integer page) {
        Specifications<Video> spe = null;
        if (cateId != null && cateId > 0) {
            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("cateId", "eq", cateId)));
        }

        if (spe == null) {
            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("isShow", BaseSpecification.EQUAL, 1)));
        }
        Page<Video> datas = videoService.findAll(spe, PageableTools.basicPage(page, "desc", "createDate"));
        return datas;
    }

    /**
     * 获取百科
     */
//    @RequestMapping(value = "encyclopediaList/{cateId}/{page}", method = RequestMethod.GET)
//    @ResponseBody
//    public Page<Encyclopedia> encyclopediaList(@PathVariable(value = "cateId") Integer cateId, @PathVariable(value = "page") Integer page) {
//        Specifications<Encyclopedia> spe = null;
//        if (cateId != null && cateId > 0) {
//            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("cateId", "eq", cateId)));
//        }
//
//        if (spe == null) {
//            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("isShow", BaseSpecification.EQUAL, 1)));
//        }
//        Page<Encyclopedia> datas = encyclopediaService.findAll(spe, PageableTools.basicPage(page, "desc", "createDate"));
//        return datas;
//    }
//
//
//    /**
//     * 获取研究报告
//     */
//    @RequestMapping(value = "reportList/{cateId}/{page}", method = RequestMethod.GET)
//    @ResponseBody
//    public Page<Report> reportList(@PathVariable(value = "cateId") Integer cateId, @PathVariable(value = "page") Integer page) {
//        Specifications<Report> spe = null;
//        if (cateId != null && cateId > 0) {
//            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("cateId", "eq", cateId)));
//        }
//
//        if (spe == null) {
//            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("isShow", BaseSpecification.EQUAL, 1)));
//        }
//        Page<Report> datas = reportService.findAll(spe, PageableTools.basicPage(page, "desc", "createDate"));
//        return datas;
//    }

}
