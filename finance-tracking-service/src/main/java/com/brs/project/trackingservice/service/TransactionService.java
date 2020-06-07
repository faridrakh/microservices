package com.brs.project.trackingservice.service;

import com.brs.project.trackingservice.entity.TransactionEntity;
import com.sytan.base.lib.ApplicationException;

import java.util.Map;

public interface TransactionService {
    void processInsertTrxn(TransactionEntity trxn) throws ApplicationException;
    void processUpdateTrxn(String id, Map<Object, Object> fields) throws ApplicationException;
    TransactionEntity processGetTrxn(String id) throws ApplicationException;
    Map<String,Object> processGetTrxnList(String userId, Integer page) throws ApplicationException;
}
