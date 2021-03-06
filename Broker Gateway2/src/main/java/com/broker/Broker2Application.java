package com.broker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
@MapperScan("com.broker.dao")
//@EnableTransactionManagement
//@EnableRedisHttpSession
public class Broker2Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Broker2Application.class, args);
    }
}
