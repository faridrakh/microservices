package com.brs.project.userservice.user.service;

import com.brs.project.userservice.user.dao.UserDAO;
import com.brs.project.userservice.user.entity.UserEntity;
import com.brs.project.userservice.usergroup.dao.UserGroupDAO;
import com.brs.project.userservice.usergroup.dao.UserGroupMtnDAO;
import com.brs.project.userservice.usergroup.entity.UserGroup;
import com.sytan.base.lib.ApplicationException;
import com.sytan.base.lib.CommonUtils;
import com.sytan.base.lib.ObjectCopier;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private UserGroupMtnDAO userGroupMtnDAO;
    private UserGroupDAO userGroupDAO;
    private CommonUtils commonUtils  = new CommonUtils();
    private ObjectCopier objectCopier  = new ObjectCopier();

    public UserServiceImpl(UserDAO userDAO, UserGroupMtnDAO userGroupMtnDAO, UserGroupDAO userGroupDAO){
        this.userDAO = userDAO;
        this.userGroupMtnDAO = userGroupMtnDAO;
        this.userGroupDAO = userGroupDAO;
    }

    @Override
    public UserEntity addUser(UserEntity user) throws ApplicationException {
        UserEntity newUser;
        String uid = commonUtils.generateUUID();
        String gid = commonUtils.generateUUID();
        user.setId(uid);
        user.setCreateBy(uid);
        user.setDtCreate(commonUtils.generateDate());

        UserGroup userGroupModel = new UserGroup();
        String mtGrpId = userGroupMtnDAO.getUserGroupMtnByCd("UL1");
        userGroupModel.setId(gid);
        userGroupModel.setMtGrpId(mtGrpId);
        userGroupDAO.addUserGrp(userGroupModel);

        user.setUserGrpId(gid);
        //user.setUserGroup(userGroupModel);
        newUser = userDAO.addUser(user);
        return newUser;
    }

    @Override
    public String updateUser(String id, Map<Object, Object> fields) {
        UserEntity uModel = userDAO.getUser(id);
        uModel = (UserEntity) objectCopier.jsonMapper(uModel,fields);
        uModel.setDtUpdate(Calendar.getInstance().getTime());
        return userDAO.updateUser(uModel);
    }

    @Override
    public String deleteUser(String id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public UserEntity getUser(String id) {
        return userDAO.getUser(id);
    }

    @Override
    public List<UserEntity> getUserList() {
        return null;
    }

    @Override
    public UserEntity getUserByUserName(String username) {
        UserEntity user = userDAO.getUserByUserName(username);
        if (null != user) {
            return user;
        }
        return null;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        UserEntity user = userDAO.getUserByEmail(email);
        if (null != user) {
            return user;
        }
        return null;

    }
}
