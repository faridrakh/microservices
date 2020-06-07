package com.brs.project.passmanager.service;

import com.brs.project.passmanager.dao.PassManagerDAO;
import com.brs.project.passmanager.entity.PasswordEntity;
import com.sytan.base.lib.ApplicationException;
import com.sytan.base.lib.CommonUtils;
import com.sytan.base.lib.ObjectCopier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class PassManagerServiceImpl implements PassManagerService {
    private PassManagerDAO passManagerDAO;
    private ObjectCopier objectCopier  = new ObjectCopier();

    private final CommonUtils commonUtils  = new CommonUtils();

    public PassManagerServiceImpl(PassManagerDAO passManagerDAO) {
        this.passManagerDAO = passManagerDAO;
    }

    @Override
    public PasswordEntity addNewPassword(PasswordEntity passVault) throws ApplicationException {
        passVault.setId(commonUtils.generateUUID());
        passVault.setDtCreate(commonUtils.generateDate());
        passVault.setCreateBy(passVault.getUsrId());
        return passManagerDAO.insertPassword(passVault);
    }

    @Override
    public String updatePassword(String id, Map<Object, Object> fields) throws ApplicationException {
        PasswordEntity tmp = passManagerDAO.getPassword(id);
        tmp = (PasswordEntity) objectCopier.jsonMapper(tmp,fields);
        tmp.setId(id);
        tmp.setDtUpdate(commonUtils.generateDate());
        tmp.setUpdateBy(tmp.getUsrId());
        return passManagerDAO.updatePassword(tmp);
    }

    @Override
    public String deletePassword(String id) throws ApplicationException {
        PasswordEntity passVault = getPasswordDetail(id);
        passVault.setDtUpdate(commonUtils.generateDate());
        passVault.setUpdateBy(passVault.getUsrId());
        passVault.setIsDel("Y");
        return passManagerDAO.updatePassword(passVault);
    }

    @Override
    public PasswordEntity getPasswordDetail(String id) throws ApplicationException {
        return passManagerDAO.getPassword(id);
    }

    @Override
    public Map<String, Object> getPasswordList(String usrId, Integer page) throws ApplicationException {
        return passManagerDAO.getListPassword(usrId,page);
    }
}
