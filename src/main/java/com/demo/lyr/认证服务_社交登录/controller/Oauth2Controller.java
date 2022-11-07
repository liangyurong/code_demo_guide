package com.demo.lyr.认证服务_社交登录.controller;

import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理社交登录请求
 */
@RestController
public class Oauth2Controller {

    @GetMapping("/oauth2/wechat/success")
    public String wechat(@RequestParam("code") String code){
        // 1、根据code获取accessToken
        Map<String,Object> map = new HashMap<>();
        String post = HttpUtil.post("https://www.baidu.com", map);
        return "";
    }

    @GetMapping("/oauth2/github/success")
    public String github(@RequestParam("code") String code){
        // 1、根据code获取accessToken
        Map<String,Object> map = new HashMap<>();
        String post = HttpUtil.post("https://www.github.com", map);
        return "";
    }

}
