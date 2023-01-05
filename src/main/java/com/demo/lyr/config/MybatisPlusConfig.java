package com.demo.lyr.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus分页插件
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 配置分页插件。配置完之后，就可以直接使用。在TestPagination.java中进行测试。
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
