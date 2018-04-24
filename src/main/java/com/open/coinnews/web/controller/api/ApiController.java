package com.open.coinnews.web.controller.api;

import com.alibaba.fastjson.JSON;
import com.open.coinnews.app.dto.CateDto;
import com.open.coinnews.app.model.*;
import com.open.coinnews.app.service.*;
import com.open.coinnews.app.tools.HtmlRegexpTools;
import com.open.coinnews.basic.auth.dto.AuthToken;
import com.open.coinnews.basic.auth.iservice.IMenuService;
import com.open.coinnews.basic.auth.iservice.IUserService;
import com.open.coinnews.basic.auth.model.User;
import com.open.coinnews.basic.auth.service.MenuServiceImpl;
import com.open.coinnews.basic.auth.tools.SecurityUtil;
import com.open.coinnews.basic.exception.SystemException;
import com.open.coinnews.basic.tools.*;
import com.open.coinnews.utils.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公共的Controller
 */
@RestController
@RequestMapping(value = "api")
public class ApiController {

    @Autowired
    private IUserService userService;
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

    @Autowired
    private IHotUpdateNotice hotUpdateNotice;

    @Autowired
    private IHotUpdateCheckOnline checkOnline;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private MenuServiceImpl menuServiceImpl;

    private static final String PATH_PRE = Utils.BASE_PATH_PRE + "public/";
    //生成二维码地址  因为目前是一个 后期会改动
    private String IOS_IMAGE_URI = "https://www.pgyer.com/fMSR";
    private String Android_IMAGE_URI = "https://www.pgyer.com/fMSR";
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
    @RequestMapping(value = "newsList/{page}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public Page<News> newsList(@PathVariable(value = "page") Integer page ,@PathVariable(value = "type") String type) {
        String codepath = createCodeImage(type);
        Specifications<News> spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("isShow", BaseSpecification.EQUAL, 1)));
        Page<News> all = newsService.findAll(spe, PageableTools.basicPage(page, "desc", "createDate"));
        for (News news : all){
            news.setCodePath(configTools.getFileBaseUrl()+"/"+codepath);
        }
        return all;
    }

    /**
     * 生成二维码
     * @return
     */
    private String createCodeImage(String type){
        String codeUrl = "";
        String codepath ="";
        String url = "";
        if (type.equals("iOS")){
            codeUrl = IOS_IMAGE_URI;
        }else {
            codeUrl = Android_IMAGE_URI;
        }
        File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/"+type);
        String path = outFile.getAbsolutePath().replace(configTools.getUploadPath(), "./upload/");
        //判断文件夹下是否有二维码  如果有就没必要再次调用生成二维码方法
        File file = new File(path);
        File [] files = file.listFiles();
        if (files==null){
            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
            //调用二维码方法
            codepath = ImageCodeUtil.createQrCode(codeUrl, path, fileName);
        }else {
            for (int i = 0; i < files.length; i++)
            {
                File file1 = files[i];
                url = file1.getName();//根据后缀判断
            }
            String replace = outFile.getAbsolutePath().replace(configTools.getUploadPath(), "");
            codepath = replace+"/"+url;
        }

       return codepath;
    }


    /**
     * 手机端获取链接
     */
    @RequestMapping(value = "urlList", method = RequestMethod.GET)
    @ResponseBody
    public Map urlList() {
        HashMap hashMap = new HashMap();
        hashMap.put("iOS",IOS_IMAGE_URI);
        hashMap.put("android",Android_IMAGE_URI);
        return hashMap;
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


    /**
     * 根据appid，version判断是否强更
     * @param
     * @return
     */
    @RequestMapping(value = "getCheckInfoOnline", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getCheckInfoOnlineByJson(@RequestParam(value = "appid", required = true) String appid,@RequestParam(value = "channelid", required = true) String channelid,@RequestParam(value = "appVersion", required = true) String appVersion) {

        ResultObject resultObject = new ResultObject();

        List<Hotupdatenotice> noticelist = hotUpdateNotice.findByNoticeAppid(appid);

        try {

            Hotupdatecheckonline hotupdatecheckonline = checkOnline.findHotupdatecheckonlineByAppidAndChannelidAndAppversion(appid, channelid);

            if (hotupdatecheckonline != null){
                if (hotupdatecheckonline != null && hotupdatecheckonline.getForcecollection() != null &&
                        !hotupdatecheckonline.getForcecollection().isEmpty()) {
                    // 强更
                    if (hotupdatecheckonline.getUpdatestrategy() == ConstantUtil.FORCE_UPDATE) {
                        if (hotupdatecheckonline.getAppversion().equals(appVersion)){
                            hotupdatecheckonline.setUpdatestrategy(ConstantUtil.NOT_UPDATE);
                        }else {
                            hotupdatecheckonline.setUpdatestrategy(ConstantUtil.FORCE_UPDATE);
                        }
                    }else if (hotupdatecheckonline.getUpdatestrategy() == ConstantUtil.NOT_UPDATE) {
                        hotupdatecheckonline.setUpdatestrategy(ConstantUtil.NOT_UPDATE);
                    } else{
                        if (hotupdatecheckonline.getForcecollection().indexOf(appVersion) != -1) {
                            hotupdatecheckonline.setUpdatestrategy(ConstantUtil.FORCE_UPDATE);
                        } else if (hotupdatecheckonline.getPromptcollection().indexOf(appVersion) != -1) {
                            hotupdatecheckonline.setUpdatestrategy(ConstantUtil.TIP_UPDATE);
                        }else {
                            hotupdatecheckonline.setUpdatestrategy(ConstantUtil.NOT_UPDATE);
                        }
                    }
                }
            }
            if(noticelist.size()>0){
                Hotupdatenotice notice = noticelist.get(0);
                Hotupdatecheckonline hotonline = checkOnline.findByAppVersion((notice.getAppversion()));
                notice.setApkurl(hotonline.getApkurl());
                notice.setUpdatestrategy(hotonline.getUpdatestrategy());

                if(notice != null && hotupdatecheckonline != null){

                    String nav = notice.getAppversion();
                    if (nav != null && !nav.isEmpty()){
                        String[] navl = nav.split("\\.");
                        String[] appVersionL = appVersion.split("\\.");

                        if (Integer.valueOf(navl[0]) > Integer.valueOf(appVersionL[0]) || Integer.valueOf(navl[1]) > Integer.valueOf(appVersionL[1])|| Integer.valueOf(navl[2]) > Integer.valueOf(appVersionL[2])){
                            notice.setUpdatestrategyStr("2");
                            resultObject.setData(notice);
                            return JsonUtil.toJSONString(resultObject);
                        }else{
                            notice.setUpdatestrategy(3);
                            notice.setUpdatestrategyStr("3");
                            resultObject.setData(notice);
                        }
                    }

                }
            }else{
                Hotupdatenotice notice = new Hotupdatenotice();
                notice.setUpdatestrategyStr("3");
                resultObject.setData(notice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultObject.setCode(CommonStatus.SUCCESS_CODE);
        resultObject.setMessage(CommonStatus.SUCCESS_MESSAGE);
        return JsonUtil.toJSONString(resultObject);
    }


    /**
     * 获取历史版本
     * @param
     * @return
     */
    @RequestMapping(value = "getHistoryHotUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getHistoryHotUpdate(@RequestParam(value = "page", required = true) Integer page,@RequestParam(value = "pageSize", required = true) Integer pageSize,HttpServletRequest request) {

        ResultObject resultObject = new ResultObject();

        try {

            Pageable pageable = new PageRequest(page, pageSize);

            Page<Hotupdatecheckonline> pageList = checkOnline.getHistoryUpdate(pageable);



            if(pageList.getContent().size()>0){
                resultObject.setData(pageList);
                resultObject.setCode(CommonStatus.SUCCESS_CODE);
                resultObject.setMessage(CommonStatus.SUCCESS_MESSAGE);
            }


        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonStatus.EXIT_FAILURE_CODE);
            resultObject.setMessage(CommonStatus.EXIT_FAILURE_MESSAGE);
        }

        return JsonUtil.toJSONString(resultObject);
    }

    /**
     * 删除版本信息
     * @param
     * @return
     */
    @RequestMapping(value = "deleteHotUpdateById", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object deleteHotUpdateById(@RequestParam(value = "id", required = true) Integer id,HttpServletRequest request) {

        ResultObject resultObject = new ResultObject();

        try {

            /*Object sess =UserLoginStatu.loginStatu(request);
            if(sess == null){
                resultObject.setCode(CommonStatus.USER_OVER_CODE);
                resultObject.setMessage(CommonStatus.USER_OVER_MESSAGE);
                return resultObject;
            }*/

            checkOnline.delete(id);
            resultObject.setCode(CommonStatus.SUCCESS_CODE);
            resultObject.setMessage(CommonStatus.SUCCESS_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonStatus.EXIT_FAILURE_CODE);
            resultObject.setMessage(CommonStatus.EXIT_FAILURE_MESSAGE);
        }

        return JsonUtil.toJSONString(resultObject);
    }


    /**
     * 新增版本信息
     * @param
     * @return
     */
    @RequestMapping(value = "insertInfor", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
   // public Object insertInfor(@RequestParam(value = "appid", required = true) String appid,@RequestParam(value = "appname", required = true) String appname,@RequestParam(value = "channelid", required = true) String channelid,@RequestParam(value = "channelname", required = true) String channelname,@RequestParam(value = "appversion", required = true) String appversion,@RequestParam(value = "apkurl", required = true) String apkurl) {
    public Object insertInfor(Hotupdatecheckonline line, HttpServletRequest request) {

        ResultObject resultObject = new ResultObject();

        try {

            Hotupdatecheckonline hotonline = checkOnline.findByAppVersion(line.getAppversion());

            if(hotonline == null){

                line.setCreatetime(new Date());

                checkOnline.save(line);

               Hotupdatenotice notice = new Hotupdatenotice();
               notice.setAppid(line.getAppid());
               notice.setAppname(line.getAppname());
               notice.setAppversion(line.getAppversion());
               notice.setUpdatemessage(line.getUpdateinfo());
               notice.setApkurl(line.getApkurl());
                notice.setCreatetime(new Date());
                notice.setUpdatetime(new Date());
                hotUpdateNotice.save(notice);

                resultObject.setCode(CommonStatus.SUCCESS_CODE);
                resultObject.setMessage(CommonStatus.SUCCESS_MESSAGE);
            }else{
                resultObject.setCode(CommonStatus.VersionIs_EROR_CODE);
                resultObject.setMessage(CommonStatus.VersionIs_EROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonStatus.EXIT_FAILURE_CODE);
            resultObject.setMessage(CommonStatus.EXIT_FAILURE_MESSAGE);
        }

        return JsonUtil.toJSONString(resultObject);
    }

    /**
     * 获取更新公告
     * @param
     * @return
     */
    @RequestMapping(value = "getHotUpdateList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getHotUpdateList(@RequestParam(value = "page", required = true) Integer page,@RequestParam(value = "pageSize", required = true) Integer pageSize, HttpServletRequest request) {

        ResultObject resultObject = new ResultObject();
        //AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);

        try {

            Pageable pageable = new PageRequest(page, pageSize);

            Page<Hotupdatenotice> pageList = hotUpdateNotice.getHotUpdateList(pageable);

            resultObject.setData(pageList);
            resultObject.setCode(CommonStatus.SUCCESS_CODE);
            resultObject.setMessage(CommonStatus.SUCCESS_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonStatus.EXIT_FAILURE_CODE);
            resultObject.setMessage(CommonStatus.EXIT_FAILURE_MESSAGE);
        }

        return JsonUtil.toJSONString(resultObject);
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object login(Model model, HttpServletRequest request) {

        ResultObject resultObject = new ResultObject();
        try {

                String errMsg = null;
                String username = request.getParameter("username"); //用户名
                String password = request.getParameter("password"); //密码
                User u = userService.findByUsername(username);
                if (u == null || u.getStatus() == null || u.getStatus() != 1) {
                    errMsg = username + "不存在或已停用";
                    resultObject.setCode(CommonStatus.USER_CHAR_CODE);
                    resultObject.setMessage(CommonStatus.USER_CHAR_MESSAGE);
                } else if (!u.getPassword().equals(SecurityUtil.md5(username, password))) {
                    errMsg = "密码输入不正确";
                    resultObject.setCode(CommonStatus.USER_PSD_CODE);
                    resultObject.setMessage(CommonStatus.USER_PSD_MESSAGE);
                } else {
                    AuthToken at = new AuthToken();
                    /*at.setLogin_ip(request.getRemoteAddr());*/
                    at.setLogin_time(new Date());
                    at.setUser(u);
                    /*at.setAuthMenu(menuServiceImpl.queryMenuDto(u.getId()));
                    List<String> authList = menuService.listAuthByUser(u.getId());*/
                    /*authList.add("AdminController.index");
                    authList.add("AdminController.updatePwd");
                    authList.add("AdminPartnerController.updateOwn");
                    at.setAuthList(authList);
                    request.getSession().setAttribute(AuthToken.SESSION_NAME, at);*/
//					request.getSession().setAttribute("login_user", u);

                }
                if (errMsg != null && !"".equals(errMsg)) {
                    //model.addAttribute("errMsg", errMsg);
                    resultObject.setCode(CommonStatus.USER_CHAR_CODE);
                    resultObject.setMessage(errMsg);
                } else {
                    resultObject.setCode(CommonStatus.LOGIN_IS_CODE);
                    resultObject.setData(u);
                    resultObject.setMessage(CommonStatus.LOGIN_IS_MESSAGE);
                }

        } catch (NoSuchAlgorithmException e) {
            resultObject.setCode(CommonStatus.LOGIN_IS_CODE);
            resultObject.setMessage(CommonStatus.LOGIN_IS_MESSAGE);
        }
        return resultObject;
    }


}
