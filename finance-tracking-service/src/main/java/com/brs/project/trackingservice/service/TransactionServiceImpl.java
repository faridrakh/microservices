package com.brs.project.trackingservice.service;

import com.brs.project.trackingservice.dao.AccountDAO;
import com.brs.project.trackingservice.dao.TransactionDAO;
import com.brs.project.trackingservice.entity.AccountEntity;
import com.brs.project.trackingservice.entity.TransactionEntity;
import com.sytan.base.lib.ApplicationException;
import com.sytan.base.lib.CommonUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Map;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private TransactionDAO transactionDAO;
    private AccountDAO accountDAO;
    private final CommonUtils commonUtils  = new CommonUtils();

    public TransactionServiceImpl(TransactionDAO transactionDAO, AccountDAO accountDAOO) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public void processInsertTrxn(TransactionEntity trxn) throws ApplicationException {
        AccountEntity account;
        String uid = commonUtils.generateUUID();
        trxn.setId(uid);
        trxn.setCreateBy(trxn.getUsrId());
        trxn.setDtCreate(commonUtils.generateDate());
        account = accountDAO.getAccount(trxn.getAccountId());
        BigDecimal total;
        if("INCOME".equals(trxn.getAction())) {
            total = account.getAmount().add(trxn.getAmount());
            trxn.setAction("0");
        } else {
            total = account.getAmount().subtract(trxn.getAmount());
            trxn.setAction("1");
        }
        account.setAmount(total);
        account.setUpdateBy(trxn.getUsrId());
        account.setDtUpdate(commonUtils.generateDate());
        accountDAO.updateAccount(account);
        transactionDAO.insertTrxn(trxn);
    }

    @Override
    public void processUpdateTrxn(String id, Map<Object, Object> fields) throws ApplicationException {
        AccountEntity account;
        TransactionEntity trxn = processGetTrxn(id);
        account = accountDAO.getAccount(fields.get("accountId").toString());
        BigDecimal total;
        trxn.setUpdateBy(fields.get("userId").toString());
        trxn.setDtUpdate(commonUtils.generateDate());
        if("INCOME".equals(fields.get("action").toString()))
            total = (account.getAmount().subtract(trxn.getAmount())).add(new BigDecimal(fields.get("amount").toString()));
        else
            total = (account.getAmount().add(trxn.getAmount())).subtract(new BigDecimal(fields.get("amount").toString()));
        account.setAmount(total);
        account.setUpdateBy(fields.get("userId").toString());
        account.setDtUpdate(commonUtils.generateDate());
        accountDAO.updateAccount(account);
        transactionDAO.updateTrxn(trxn);
    }

    @Override
    public TransactionEntity processGetTrxn(String id) throws ApplicationException {
        return transactionDAO.getTrxn(id);
    }

    @Override
    public Map<String, Object> processGetTrxnList(String userId, Integer page) throws ApplicationException {
        return transactionDAO.getTrxnList(userId,page);
    }
}
