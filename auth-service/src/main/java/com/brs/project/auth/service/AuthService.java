package com.brs.project.auth.service;

import java.util.Map;

public interface AuthService {
    Map<String,Object> loadUserDetailFromUserName(String userName, String password);
}
