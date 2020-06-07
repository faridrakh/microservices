package com.brs.project.trackingservice.dao;

import com.brs.project.trackingservice.entity.TransactionEntity;
import com.sytan.base.lib.ApplicationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
    private EntityManager entityManager;

    public TransactionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insertTrxn(TransactionEntity trxn) throws ApplicationException {
        entityManager.persist(trxn);
    }

    @Override
    public void updateTrxn(TransactionEntity trxn) throws ApplicationException {
        entityManager.merge(trxn);
    }

    @Override
    public TransactionEntity getTrxn(String id) throws ApplicationException {
        TransactionEntity result;
        String qr = "FROM TransactionEntity u WHERE u.id = :id";
        Query query = entityManager.createQuery(qr);
        query.setParameter("id", id);
        result = (TransactionEntity) query.getSingleResult();
        return result;
    }

    @Override
    public Map<String, Object> getTrxnList(String userId, Integer page) throws ApplicationException {
        Map<String,Object> res = new LinkedHashMap<>();
        int pageSize = 5 * page;
        Query countQuery = entityManager.createQuery("Select count(p.id) FROM TransactionEntity p where p.usrId = :usrId");
        countQuery.setParameter("usrId", userId);
        Long countResults = (Long) countQuery.getSingleResult();

        String qr = "FROM TransactionEntity p where p.usrId = :usrId ORDER BY p.dtCreate DESC";
        Query query = entityManager.createQuery(qr);
        query.setParameter("usrId", userId);
        query.setFirstResult(0);
        query.setMaxResults(pageSize);
        List<TransactionEntity> list = query.getResultList();
        res.put("page", page);
        res.put("perPage", pageSize);
        res.put("total", countResults);
        res.put("transactionList", list);
        return res;
    }
}
