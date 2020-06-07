package com.brs.project.trackingservice.service;

import com.brs.project.trackingservice.dao.AccountDAO;
import com.brs.project.trackingservice.entity.AccountEntity;
import com.sytan.base.lib.ApplicationException;
import com.sytan.base.lib.CommonUtils;
import com.sytan.base.lib.ObjectCopier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;
    private final CommonUtils commonUtils  = new CommonUtils();
    private ObjectCopier objectCopier  = new ObjectCopier();

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void processAddAccount(AccountEntity account) throws ApplicationException {
        String uid = commonUtils.generateUUID();
        account.setId(uid);
        account.setCreateBy(account.getUsrId());
        account.setDtCreate(commonUtils.generateDate());
        accountDAO.addAccount(account);
    }

    @Override
    public void processUpdateAccount(String id, Map<Object, Object> fields) throws ApplicationException {
        AccountEntity tmp = accountDAO.getAccount(id);
        tmp = (AccountEntity) objectCopier.jsonMapper(tmp,fields);
        tmp.setId(id);
        tmp.setDtUpdate(commonUtils.generateDate());
        tmp.setUpdateBy(tmp.getUsrId());
        accountDAO.updateAccount(tmp);
    }

    @Override
    public AccountEntity processGetAccount(String uid, String aid) throws ApplicationException {
        return accountDAO.getAccount(uid, aid);
    }

    @Override
    public Map<String, Object> processGetAccountList(String userId, Integer page) throws ApplicationException {
        return accountDAO.getAccountList(userId, page);
    }
}
