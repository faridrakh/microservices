package com.brs.project.userservice.usergroup.dao;

import com.brs.project.userservice.usergroup.entity.UserGroup;

public interface UserGroupDAO {
    UserGroup addUserGrp(UserGroup userGrp);
    UserGroup getUserGroupCodeById(String userId);
}
