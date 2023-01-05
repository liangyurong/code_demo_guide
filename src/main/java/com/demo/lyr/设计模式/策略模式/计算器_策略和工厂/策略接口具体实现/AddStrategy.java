package com.demo.lyr.设计模式.策略模式.计算器_策略和工厂.策略接口具体实现;

import com.demo.lyr.设计模式.策略模式.计算器_策略和工厂.config.CalMethod;
import com.demo.lyr.设计模式.策略模式.计算器_策略和工厂.策略接口.CalStrategy;
import org.springframework.stereotype.Service;

@CalMethod(value = "add",desc = "加法")
@Service
public class AddStrategy implements CalStrategy {
    @Override
    public int calculate(int num1, int num2) {
        return num1+num2;
    }
}
