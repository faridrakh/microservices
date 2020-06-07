package com.brs.project.passmanager.dao;

import com.brs.project.passmanager.entity.PasswordEntity;
import com.sytan.base.lib.ApplicationException;

import java.util.Map;

public interface PassManagerDAO {
    PasswordEntity insertPassword(PasswordEntity passVault) throws ApplicationException;
    String updatePassword(PasswordEntity passVault) throws ApplicationException ;
    String deletePassword(String id) throws ApplicationException ;
    PasswordEntity getPassword(String id) throws ApplicationException ;
    Map<String,Object> getListPassword(String usrId, Integer page) throws ApplicationException ;
}
