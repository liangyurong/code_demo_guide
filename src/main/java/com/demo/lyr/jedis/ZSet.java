package com.demo.lyr.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class ZSet {
    private String ip = "192.168.64.129";
    private int port = 6379;

    @Test
    public void setGet() {
        Jedis jedis = new Jedis(ip, port);

        jedis.zadd("k", 100, "java");
        jedis.zadd("k", 200, "C++");
        jedis.zadd("k", 300, "go");

        // 不带分数的排序
        Set<String> set = jedis.zrange("k", 0, -1);
        System.out.println(set);

        // 带分数的排序
        jedis.zrangeWithScores("k", 0, -1).forEach(System.out::println);

        // 关闭链接
        jedis.close();

    }
}
