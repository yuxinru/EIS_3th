package com.trader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
//@MapperScan("com.bookstore.dao")
public class TraderApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TraderApplication.class, args);
    }
}
