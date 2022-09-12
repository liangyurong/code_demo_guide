package com.demo.lyr.redis_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/test")
    public String add(){
        redisTemplate.opsForValue().set("name","yurong222");
        Object name = redisTemplate.opsForValue().get("name");
        return name.toString();
    }

}
