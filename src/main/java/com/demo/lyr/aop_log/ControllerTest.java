package com.demo.lyr.aop_log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @Autowired
    private CalImpl cal;

    // 启动主类，在浏览器输入：http://localhost:9090/log ， 可以得到日志信息
    @GetMapping("/logtest")
    public int test(){
        return cal.add(19,8);
    }

}
