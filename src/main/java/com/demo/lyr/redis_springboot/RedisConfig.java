package com.demo.lyr.redis_springboot;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

}
