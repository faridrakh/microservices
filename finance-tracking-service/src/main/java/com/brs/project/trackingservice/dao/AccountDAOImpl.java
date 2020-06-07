package com.brs.project.trackingservice.dao;

import com.brs.project.trackingservice.entity.AccountEntity;
import com.sytan.base.lib.ApplicationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private EntityManager entityManager;

    public AccountDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addAccount(AccountEntity account) throws ApplicationException {
        entityManager.persist(account);
    }

    @Override
    public void updateAccount(AccountEntity account) throws ApplicationException {
        entityManager.merge(account);
    }

    @Override
    public AccountEntity getAccount(String uid) throws ApplicationException {
        AccountEntity result;
        String qr = "FROM AccountEntity u WHERE u.id = :id";
        Query query = entityManager.createQuery(qr);
        query.setParameter("id", uid);
        result = (AccountEntity) query.getSingleResult();
        return result;
    }

    @Override
    public AccountEntity getAccount(String uid, String accountId) throws ApplicationException {
        AccountEntity result;
        String qr = "FROM AccountEntity u WHERE u.usrId = :id and u.id = :aid";
        Query query = entityManager.createQuery(qr);
        query.setParameter("id", uid);
        query.setParameter("aid",  accountId);
        result = (AccountEntity) query.getSingleResult();
        return result;
    }

    @Override
    public Map<String, Object> getAccountList(String userId, Integer page) throws ApplicationException {
        Map<String,Object> res = new LinkedHashMap<>();
        int pageSize = 5 * page;
        Query countQuery = entityManager.createQuery("Select count(p.id) FROM AccountEntity p where p.usrId = :usrId");
        countQuery.setParameter("usrId", userId);
        Long countResults = (Long) countQuery.getSingleResult();

        String qr = "FROM AccountEntity p where p.usrId = :usrId ORDER BY p.dtCreate DESC";
        Query query = entityManager.createQuery(qr);
        query.setParameter("usrId", userId);
        query.setFirstResult(0);
        query.setMaxResults(pageSize);
        List<AccountEntity> list = query.getResultList();
        res.put("page", page);
        res.put("perPage", pageSize);
        res.put("total", countResults);
        res.put("accountList", list);
        return res;
    }
}
