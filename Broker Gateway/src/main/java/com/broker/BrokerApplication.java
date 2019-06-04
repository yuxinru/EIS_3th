package com.broker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJms
@SpringBootApplication
@MapperScan("com.broker.dao")
@EnableTransactionManagement
public class BrokerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BrokerApplication.class, args);

    }


}
