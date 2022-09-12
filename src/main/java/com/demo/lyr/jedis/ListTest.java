package com.demo.lyr.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ListTest {

    private String ip = "192.168.64.129";
    private int port = 6379;

    @Test
    public void pushGet(){
        Jedis jedis = new Jedis(ip,port);

        jedis.lpush("names", "小明1", "小明2", "小明3", "小明4");

        List<String> names = jedis.lrange("names", 0, -1);

        names.forEach(System.out::println);

        // 关闭链接
        jedis.close();
    }

}
