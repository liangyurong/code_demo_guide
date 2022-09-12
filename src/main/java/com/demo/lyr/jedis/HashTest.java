package com.demo.lyr.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class HashTest {
    private String ip = "192.168.64.129";
    private int port = 6379;

    @Test
    public void setGet(){
        Jedis jedis = new Jedis(ip,port);

        jedis.hset("user","id","01");
        jedis.hset("user","name","yurong333");
        jedis.hset("user","age","23");

        // 获取key的所有field
        Set<String> user = jedis.hkeys("user");
        System.out.println(user);

        // 关闭链接
        jedis.close();

    }
}
