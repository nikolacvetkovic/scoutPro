package com.riocode.scoutpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ScoutProApp {
    public static void main( String[] args ){
        SpringApplication.run(ScoutProApp.class, args);
    }
}
