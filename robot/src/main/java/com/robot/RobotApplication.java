package com.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class RobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotApplication.class, args);
    }

}
