package com.demo.lyr.定时任务.单个线程池执行多任务;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling // 开启定时任务(该注解也可以添加到启动类)
public class QuartzConfig {
}
