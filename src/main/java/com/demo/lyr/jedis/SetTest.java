package com.demo.lyr.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class SetTest {

    private String ip = "192.168.64.129";
    private int port = 6379;

    // 自动去重
    @Test
    public void add(){
        Jedis jedis = new Jedis(ip,port);

        jedis.sadd("name","1","2","3","1","2");
        Set<String> names = jedis.smembers("name");
        names.forEach(System.out::println);

        // 关闭链接
        jedis.close();
    }



}
