package com.mybitop.gameversioncontrol.conf;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * 加入@ EnableCaching开启缓存技术
 * 在需要缓存的地方加入@Cacheable注解
 */
@Configuration
@EnableCaching
public class CacheConfiguration {
}
