package com.brs.project.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProjectUserService {
    public static void main(String[] args) {
        SpringApplication.run(ProjectUserService.class,args);
    }
}
