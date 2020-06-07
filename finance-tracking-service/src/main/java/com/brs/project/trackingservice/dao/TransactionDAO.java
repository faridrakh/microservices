package com.brs.project.trackingservice.dao;

import com.brs.project.trackingservice.entity.TransactionEntity;
import com.sytan.base.lib.ApplicationException;

import java.util.Map;

public interface TransactionDAO {
    void insertTrxn(TransactionEntity trxn) throws ApplicationException;
    void updateTrxn(TransactionEntity trxn) throws ApplicationException;
    TransactionEntity getTrxn(String id) throws ApplicationException;
    Map<String,Object> getTrxnList(String userId, Integer page) throws ApplicationException;
}
