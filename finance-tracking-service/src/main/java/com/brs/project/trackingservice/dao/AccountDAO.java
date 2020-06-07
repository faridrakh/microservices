package com.brs.project.trackingservice.dao;

import com.brs.project.trackingservice.entity.AccountEntity;
import com.sytan.base.lib.ApplicationException;

import java.util.Map;

public interface AccountDAO {
    void addAccount(AccountEntity account) throws ApplicationException;
    void updateAccount(AccountEntity account) throws ApplicationException;
    AccountEntity getAccount(String uid) throws ApplicationException;
    AccountEntity getAccount(String uid, String accountId) throws ApplicationException;
    Map<String,Object> getAccountList(String userId, Integer page) throws ApplicationException;
}
