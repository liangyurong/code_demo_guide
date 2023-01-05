package com.demo.lyr.设计模式.策略模式.计算器_策略和工厂;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalController {

    @Autowired
    private CalService service;

    // http://localhost:9090/cal?inputVal=div&num1=1&num2=3
    @GetMapping("cal")
    public int cal(@RequestParam String inputVal,@RequestParam int num1,@RequestParam int num2){
       return service.select(inputVal,num1,num2);
    }

}
