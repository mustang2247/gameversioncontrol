package com.mybitop.gameversioncontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class GameversioncontrolApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(GameversioncontrolApplication.class, args);

//        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
//        CountDownLatch latch = ctx.getBean(CountDownLatch.class);
//
////		LOGGER.info("Sending message...");
//        template.convertAndSend("chat", "Hello from Redis!");
//
//        latch.await();
//
//        System.exit(0);
    }
}
