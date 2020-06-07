package com.brs.project.userservice.usergroup.service;

import com.brs.project.userservice.usergroup.entity.UserGroup;
import com.sytan.base.lib.ApplicationException;

public interface UserGroupService {
    void addUserGrp(String userId) throws ApplicationException;
    UserGroup getUserGrp(String userId) throws ApplicationException;
}
