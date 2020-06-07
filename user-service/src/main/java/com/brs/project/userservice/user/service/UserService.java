package com.brs.project.userservice.user.service;

import com.brs.project.userservice.user.entity.UserEntity;
import com.sytan.base.lib.ApplicationException;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserEntity addUser(UserEntity user) throws ApplicationException;
    String updateUser(String id, Map<Object, Object> fields);
    String deleteUser(String id);
    UserEntity getUser(String id);
    List<UserEntity> getUserList();
    UserEntity getUserByUserName(String username);
    UserEntity getUserByEmail(String email);
}
