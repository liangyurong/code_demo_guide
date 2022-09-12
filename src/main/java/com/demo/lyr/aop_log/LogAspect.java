package com.demo.lyr.aop_log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect    // 这是一个切面
@Component // 这是一个Bean
public class LogAspect {

    // 切面，监控value里面的方法
    // 第一个* ：返回值类型
    // 第二个* ：类
    // 第三个* ：方法
    // .. ： 参数
    @Pointcut(value = "execution(public * com.demo.lyr.aop_log.*.*(..))")
    public void webLog() {
    }

    // 把目标方法和切面对象JoinPoint连接起来
    // @Before: 在目标方法执行之前
    // value里面是切面方法，也就是需要监控的方法
    @Before(value = "webLog()")
    public void before(JoinPoint joinPoint){

        // 通过切面对象获取方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println("方法名："+methodName);

        // 方法的参数
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("方法参数："+args);
    }

    // 方法返回结果
    // returning绑定返回结果
    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 处理完请求，返回内容
        System.out.println("返回的参数 : " + result);
    }



}
