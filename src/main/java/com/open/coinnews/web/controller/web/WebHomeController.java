package com.open.coinnews.web.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.open.coinnews.app.dto.Market;
import com.open.coinnews.app.model.Article;
import com.open.coinnews.app.model.News;
import com.open.coinnews.app.model.TopPic;
import com.open.coinnews.app.service.*;
import com.open.coinnews.basic.tools.BaseSpecification;
import com.open.coinnews.basic.tools.PageableTools;
import com.open.coinnews.basic.tools.SearchCriteria;
import com.open.coinnews.basic.tools.SortTools;
import com.open.coinnews.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 网站首页
 */
@Controller
public class WebHomeController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IAboutService aboutService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ITopPicService topPicService;

    @Autowired
    private INewsService newsService;

    @Value("${COINMARKET_REST_PATH}")
    private String COINMARKET_REST_PATH;

    /**
     * 网站首页
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(Model model, Integer userId, Integer cateId, String tag, String condition, Integer page) {
        Specifications<Article> spe = null;
        if (userId != null && userId > 0) {
            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("userId", "eq", userId)));
        } else if (cateId != null && cateId > 0) {
            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("cateId", "eq", cateId)));
            model.addAttribute("category", categoryService.findOne(cateId)); //获取当前分类
        } else if (tag != null && !"".equals(tag.trim())) {
            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("tags", "like", tag)));
        }
        if (condition != null && !"".equalsIgnoreCase(condition)) {
            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("title", "like", condition)));
            spe = spe.or(new BaseSpecification<>(new SearchCriteria("mdContent", "like", condition)));
        }

        List<TopPic> pics = topPicService.findAll(Specifications.where(new BaseSpecification<>(new SearchCriteria("status", "eq", "1"))), SortTools.basicSort("asc", "orderNo"));
        model.addAttribute("topPics", pics);

        if (spe == null) {
            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("isShow", BaseSpecification.EQUAL, 1)));
        }
        Page<Article> datas = articleService.findAll(spe, PageableTools.basicPage(page, "desc", "createDate"));
        model.addAttribute("datas", datas);
        return "web/index";
//        return "index_redirect";
    }

    /**
     * 测试ckeditor
     * @param model
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String home(Model model) {

        return "test/index";
    }

    @RequestMapping(value = "kuaixun", method = RequestMethod.GET)
    public String kuaixun(Model model, Integer page) {
        Specifications<News> spe = null;
        if (spe == null) {
            spe = Specifications.where(new BaseSpecification<>(new SearchCriteria("isShow", BaseSpecification.EQUAL, 1)));
        }
        Page<News> datas = newsService.findAll(spe, PageableTools.basicPage(page, "desc", "createDate"));
        model.addAttribute("datas", datas);
        return "ws/index";
    }

    /**
     * 关于
     */
    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String about(Model model) {
        model.addAttribute("about", aboutService.findOne(1));
        return "web/about";
    }


    @RequestMapping(value = "ticker", method = RequestMethod.GET)
    public String trades(Model model, Integer page) {
        List<Market> list=null;
        //调用接口返回结果
        String result= HttpClientUtil.doGet(COINMARKET_REST_PATH);
        System.out.println("result   ："+result);
        //JSONObject jsonObject = JSON.parseObject(result);
        //if(!jsonObject.isEmpty()){
          //Integer status= (Integer)jsonObject.get("status");
            //if (status ==200){
                //JSONArray data = jsonObject.getJSONArray("data");

       /* String jsondata=data.toString();
                System.out.println("data："+data);
                System.out.println("jsondata："+jsondata);*/
                list = JSONObject.parseArray(result, Market.class);//把字符串转换成集合
           // }else{}
       // }else{

       // }

        System.out.println("调用行情接口返回值："+result+"     获取长度："+list.size());
        model.addAttribute("market", list);
        return "web/market";
    }
}
