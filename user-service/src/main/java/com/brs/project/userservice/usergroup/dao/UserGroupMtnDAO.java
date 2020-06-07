package com.brs.project.userservice.usergroup.dao;

import com.brs.project.userservice.usergroup.entity.UserGroupMtn;
import com.sytan.base.lib.ApplicationException;

import java.util.List;

public interface UserGroupMtnDAO {
    List<UserGroupMtn> getUserGroupMtnList() throws ApplicationException;
    String getUserGroupMtnByCd(String cd) throws ApplicationException;
}
