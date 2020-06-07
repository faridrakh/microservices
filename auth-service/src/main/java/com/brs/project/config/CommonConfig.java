package com.brs.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonConfig {
    @Value("${user-service.endpoint}")
    private String userEndpoint;

    public String getUserEndpoint() {
        return userEndpoint;
    }
}
