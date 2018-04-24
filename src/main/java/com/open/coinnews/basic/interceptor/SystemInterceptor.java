package com.open.coinnews.basic.interceptor;

import com.open.coinnews.app.model.Category;
import com.open.coinnews.app.service.ICategoryService;
import com.open.coinnews.basic.iservice.IAppConfigService;
import com.open.coinnews.basic.model.AppConfig;
import com.open.coinnews.basic.tools.SortTools;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 拦截器
 */
@Configuration
public class SystemInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IAppConfigService appConfigService;

    @Autowired
    private ICategoryService categoryService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //将系统配置文件存入Session中
        AppConfig appConfig = (AppConfig) session.getAttribute("appConfig");
        if(appConfigService==null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            appConfigService = (IAppConfigService) factory.getBean("appConfigService");
        }
        if(appConfig==null) {
            appConfig = appConfigService.loadOne();
            session.setAttribute("appConfig", appConfig);
        }

        List<Category> cateList = (List<Category>) session.getAttribute("navCateList");
        if(cateList==null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            categoryService = (ICategoryService) factory.getBean("categoryService");
            cateList = categoryService.findNavCate(SortTools.basicSort("asc", "orderNo"));
            session.setAttribute("navCateList", cateList);
        }
        return super.preHandle(request, response, handler);
    }
}