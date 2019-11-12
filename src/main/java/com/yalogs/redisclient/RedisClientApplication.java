package com.yalogs.redisclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RedisClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisClientApplication.class, args);
    }

}
