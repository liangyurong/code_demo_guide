package com.demo.lyr.网络;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class HttpController {

    @GetMapping("/test")
    public void test(HttpServletRequest request) {
        // session
        HttpSession session = request.getSession();
        System.out.println("session = " + session);
        // cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String path = cookie.getPath();
            String domain = cookie.getDomain();
            System.out.println("name = " + name);
            System.out.println("path = " + path);
            System.out.println("domain = " + domain);
        }
    }

}
