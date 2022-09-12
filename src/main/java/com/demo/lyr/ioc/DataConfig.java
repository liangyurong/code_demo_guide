package com.demo.lyr.ioc;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("config")
public class DataConfig {
    @Value("localhost:3306/lyr")
    private String url;
    @Value("Driver")
    private String driverName;
    @Value("root")
    private String username;
    @Value("123456")
    private String password;
}
