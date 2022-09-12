package com.demo.lyr.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class KeyTest {

    private String ip = "192.168.64.129";
    private int port = 6379;

    @Test
    public void allKeys(){
        // 创建对象
        Jedis jedis = new Jedis(ip,port);

        Set<String> keys = jedis.keys("*");

        // 第一种循环
        Iterator<String> it = keys.iterator();
        while (it.hasNext()){
            String next = it.next();
            System.out.println(next);
        }

        // 第二种循环
        for (String key:keys){
            System.out.println(key);
        }

        // 第三种循环
        keys.forEach(System.out::println);

        // 关闭链接
        jedis.close();

    }

    @Test
    public void typeKey(){
        Jedis jedis = new Jedis(ip,port);

        String type = jedis.type("name");
        System.out.println(type);

        // 关闭链接
        jedis.close();
    }

    @Test
    public void addKey(){
        Jedis jedis = new Jedis(ip,port);

        jedis.set("name1","yurong333");

        String name = jedis.get("name");

        System.out.println(name);

        // 关闭链接
        jedis.close();
    }

    @Test
    public void addManyKeyVal(){
        Jedis jedis = new Jedis(ip,port);

        jedis.mset("key1","val1","key2","val2");

        List<String> vals = jedis.mget("key1", "key2");

        for(String val:vals){
            System.out.println(val);
        }

        // 关闭链接
        jedis.close();
    }

    // true 存在
    @Test
    public void existKey(){
        Jedis jedis = new Jedis(ip,port);

        Boolean b = jedis.exists("name");
        System.out.println(b);

        // 关闭链接
        jedis.close();

    }

    // -1 永不过期
    @Test
    public void ttlKey(){
        Jedis jedis = new Jedis(ip,port);

        Long time = jedis.ttl("name");
        System.out.println(time);

        // 关闭链接
        jedis.close();
    }



}
