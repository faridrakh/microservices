package com.brs.project.userservice.usergroup.service;

import com.brs.project.userservice.usergroup.dao.UserGroupMtnDAO;
import com.brs.project.userservice.usergroup.entity.UserGroupMtn;
import com.sytan.base.lib.ApplicationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserGroupMtnServiceImpl implements UserGroupMtnService {

    private UserGroupMtnDAO userGroupMtnDAO;

    public UserGroupMtnServiceImpl(UserGroupMtnDAO userGroupMtnDAO){
        this.userGroupMtnDAO = userGroupMtnDAO;
    }

    @Override
    public List<UserGroupMtn> getUserGroupMtnList() throws ApplicationException {
        List<UserGroupMtn> UserGroupMtnList = userGroupMtnDAO.getUserGroupMtnList();
        return UserGroupMtnList;
    }
}
