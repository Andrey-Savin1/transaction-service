package com.example.transactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class TransactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }

//    SpringApplication app = new SpringApplication(TransactionServiceApplication.class);
//    ConfigurableApplicationContext context = app.run(args);
//    String[] activeProfiles = context.getEnvironment().getActiveProfiles();
//        System.out.println("Active profiles: " + Arrays.toString(activeProfiles));
}
