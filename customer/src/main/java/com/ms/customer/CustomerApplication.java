package com.ms.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerApplication {

    @Bean
    public Logger logger(){
        return LoggerFactory.getLogger("CustomerModuleLogger");
    }


    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
