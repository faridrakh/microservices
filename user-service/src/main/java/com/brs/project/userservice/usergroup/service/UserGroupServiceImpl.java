package com.brs.project.userservice.usergroup.service;

import com.brs.project.userservice.usergroup.dao.UserGroupDAO;
import com.brs.project.userservice.usergroup.dao.UserGroupMtnDAO;
import com.brs.project.userservice.usergroup.entity.UserGroup;
import com.sytan.base.lib.ApplicationException;
import com.sytan.base.lib.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService {
    private UserGroupDAO userGroupDAO;
    private UserGroupMtnDAO userGroupMtnDAO;

    private final CommonUtils commonUtils = new CommonUtils();

    public UserGroupServiceImpl(UserGroupDAO userGroupDAO,UserGroupMtnDAO userGroupMtnDAO){
        this.userGroupDAO = userGroupDAO;
        this.userGroupMtnDAO = userGroupMtnDAO;
    }

    @Override
    public void addUserGrp(String userId) throws ApplicationException {
        UserGroup userGroupModel = new UserGroup();
        String uuid = commonUtils.generateUUID();
        String mtGrpId = userGroupMtnDAO.getUserGroupMtnByCd("UL1");
        userGroupModel.setId(uuid);
        userGroupModel.setMtGrpId(mtGrpId);
        userGroupDAO.addUserGrp(userGroupModel);
    }

    @Override
    public UserGroup getUserGrp(String id) throws ApplicationException {
        return userGroupDAO.getUserGroupCodeById(id);
    }
}
