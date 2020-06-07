package com.brs.project.userservice.user.dao;

import com.brs.project.userservice.user.entity.UserEntity;

import java.util.List;

public interface UserDAO {
    UserEntity addUser(UserEntity user);
    String updateUser(UserEntity user);
    String deleteUser(String id);
    UserEntity getUser(String id);
    UserEntity getUser(String user,String password);
    UserEntity getUserByUserName(String value);
    List<UserEntity> getUserList();
    UserEntity getUserByEmail(String value);
}
