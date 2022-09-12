package com.demo.lyr.jedis;

import redis.clients.jedis.Jedis;

public class ConnectRedis {

    public static void main(String[] args) {
        connect();
    }

    public static void connect(){
        String  ip = "192.168.64.129";
        int port = 6379;

        // 创建对象
        Jedis jedis = new Jedis(ip,port);

        // 测试连接，连接成功返回PONG
        String ping = jedis.ping();
        System.out.println(ping);

        // 关闭链接
        jedis.close();

    }

}
