package com.brs.project.trackingservice.service;

import com.brs.project.trackingservice.entity.AccountEntity;
import com.sytan.base.lib.ApplicationException;

import java.util.Map;

public interface AccountService {
    void processAddAccount(AccountEntity account) throws ApplicationException;
    void processUpdateAccount(String id, Map<Object, Object> fields) throws ApplicationException;
    AccountEntity processGetAccount(String uid, String aid) throws ApplicationException;
    Map<String,Object> processGetAccountList(String userId, Integer page) throws ApplicationException;
}
