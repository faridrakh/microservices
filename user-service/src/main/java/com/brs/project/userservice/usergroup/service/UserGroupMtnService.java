package com.brs.project.userservice.usergroup.service;

import com.brs.project.userservice.usergroup.entity.UserGroupMtn;
import com.sytan.base.lib.ApplicationException;

import java.util.List;

public interface UserGroupMtnService {
    List<UserGroupMtn> getUserGroupMtnList() throws ApplicationException;
}
