package com.mybitop.gameversioncontrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
@EnableScheduling
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
//        try {
//            System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","|{}");
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        SpringApplication.run(Application.class, args);
    }


}
