package com.brs.project.passmanager.service;

import com.brs.project.passmanager.entity.PasswordEntity;
import com.sytan.base.lib.ApplicationException;

import java.util.Map;

public interface PassManagerService {
    PasswordEntity addNewPassword(PasswordEntity passVault) throws ApplicationException;
    String updatePassword(String id, Map<Object, Object> fields) throws ApplicationException;
    String deletePassword(String id) throws ApplicationException;
    PasswordEntity getPasswordDetail(String id) throws ApplicationException;
    Map<String,Object> getPasswordList(String usrId, Integer page) throws ApplicationException;
}
