package com.demo.lyr.定时任务.单个线程池执行多任务;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalDate;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync // 开启对异步的支持
public class ThreadPoolConfig {

    /**
     * 配置第1个线程池
     */
    @Bean("executor1")
    public Executor executor1() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("线程池1：");
        executor.setMaxPoolSize(20);
        executor.setCorePoolSize(15);
        executor.setQueueCapacity(0);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}

