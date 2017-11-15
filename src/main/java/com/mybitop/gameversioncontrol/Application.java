package com.mybitop.gameversioncontrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@EnableCaching
//@ControllerAdvice
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Starting");
    }

//    /**
//     * 配置springmvc异常处理器
//     * 处理 未授权（AuthorizationException）页面
//     * @param e
//     * @param model
//     * @return
//     */
//    @ExceptionHandler(AuthorizationException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public String handleException(AuthorizationException e, Model model) {
//
//        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
//        // resource at that location)
//        logger.debug("AuthorizationException was thrown", e);
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("status", HttpStatus.FORBIDDEN.value());
//        map.put("message", "No message available 403");
//        model.addAttribute("errors", map);
//
//        return "error";
//    }

//    /**
//     * 为所有的request添加用户主体
//     * @return
//     */
//    @ModelAttribute(name = "subject")
//    public Subject subject() {
//        return SecurityUtils.getSubject();
//    }

}
