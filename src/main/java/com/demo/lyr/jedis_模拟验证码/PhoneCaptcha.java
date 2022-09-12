package com.demo.lyr.jedis_模拟验证码;

import redis.clients.jedis.Jedis;
import java.util.Random;

public class PhoneCaptcha {

    public static void main(String[] args) {

        // 手机号码
        String phone = "123456";

        // 手机是否已经验证3次
        boolean verifyPhone =  verifyPhoneCount(phone);

        if(!verifyPhone){
            System.out.println("今日验证次数已用完，请明天再试");
            return;
        }

        // 生成验证码
        String code = getCode();

        // 存储验证码到redis
        saveCode(phone,code);

        // 手机输入验证码，验证是否正确
        verifyCode(phone,"174014");

    }


    // 生成6位随机数字
    public static String getCode(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int ran = random.nextInt(10);
            sb.append(ran);
        }
        return sb.toString();
    }

    // 一个手机号一天只能接收3次
    public static boolean verifyPhoneCount(String phone){
        Jedis jedis = new Jedis( "192.168.64.129",6379);

        // 自定义：手机发送次数key
        String countKey = phone+":count";

        // 验证是否使用了3次
        String count = jedis.get(countKey);

        if(null == count){
            // 没有发送次数，则设置为1
            // 24小时之后才能重新进行验证
            jedis.setex(countKey,24*60*60,"1");
            jedis.close();
            return true;
        }

        if( Integer.parseInt(count)<= 2 ){
            // 验证次数<=2 ，次数+1
            jedis.incr(countKey);
            jedis.close();
            return true;
        }

        if( Integer.parseInt(count) >= 3 ){
            jedis.close();
            return false;
        }

        return false;

    }

    // 验证码存储到redis
    public static void saveCode(String phone,String code){
        Jedis jedis = new Jedis( "192.168.64.129",6379);

        // 自定义：验证码key
        // 这里将手机号和验证码绑定起来作为key，可以防止非本手机号进行验证码的验证
        String codeKey = phone+":code";

        // 存储到redis，并设置过期时间
        jedis.setex(codeKey,120,code);

        jedis.close();

    }

    // 验证码校验
    public static void verifyCode(String phone,String code){
        Jedis jedis = new Jedis( "192.168.64.129",6379);

        // 自定义：验证码key
        String codeKey = phone+":code";

        // 从redis中获取验证码
        String redisCode = jedis.get(codeKey);

        if(code.equals(redisCode)){
            System.out.println("验证成功");
        }else {
            System.out.println("验证失败");
        }

        jedis.close();

    }

}
