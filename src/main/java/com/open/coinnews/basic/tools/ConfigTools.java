package com.open.coinnews.basic.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 文件上传
 */
@Configuration
@Component
public class ConfigTools { // extends WebMvcConfigurerAdapter

    @Value("${web.upload-path}")
    private String uploadPath;

    @Value("${web.file.base.url}")
    private String fileBaseUrl;

    public String getUploadPath() {
        return getUploadPath("");
    }

    public String getUploadPath(String basePath) {
        File f = new File(uploadPath+basePath);
        if(!f.exists()) {f.mkdirs();}
        return f.getAbsolutePath();
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getFileBaseUrl(){
        return fileBaseUrl;
    }

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //设置静态资源路径
        registry.addResourceHandler("*//**")
                .addResourceLocations("classpath:/", "classpath:/static/", "classpath:/public/", "file:"+File.separator+uploadPath);
//        super.addResourceHandlers(registry);
    }*/
}
