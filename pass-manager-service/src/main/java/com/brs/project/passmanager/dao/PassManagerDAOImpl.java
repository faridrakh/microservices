package com.brs.project.passmanager.dao;

import com.brs.project.passmanager.entity.PasswordEntity;
import com.sytan.base.lib.ApplicationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PassManagerDAOImpl implements PassManagerDAO {
    private EntityManager em;

    public PassManagerDAOImpl(EntityManager em){
        this.em = em;
    }
    @Override
    public PasswordEntity insertPassword(PasswordEntity passVault) throws ApplicationException {
        em.persist(passVault);
        return passVault;
    }

    @Override
    public String updatePassword(PasswordEntity passVault) throws ApplicationException {
        em.merge(passVault);
        return "SUCCESS";
    }

    @Override
    public String deletePassword(String id) throws ApplicationException {
        PasswordEntity passVault = new PasswordEntity();
        passVault.setId(id);
        em.remove(passVault);
        return "SUCCESS";
    }

    @Override
    public PasswordEntity getPassword(String id) throws ApplicationException {
        PasswordEntity result;
        String qr = "FROM PasswordEntity u WHERE u.id = :id";
        Query query = em.createQuery(qr);
        query.setParameter("id", id);
        result = (PasswordEntity) query.getSingleResult();
        return result;
    }

    @Override
    public Map<String, Object> getListPassword(String usrId, Integer page) throws ApplicationException {
        Map<String,Object> res = new LinkedHashMap<>();
        int pageSize = 5 * page;
        Query countQuery = em.createQuery("Select count(p.id) FROM PasswordEntity p where p.usrId = :usrId");
        countQuery.setParameter("usrId", usrId);
        Long countResults = (Long) countQuery.getSingleResult();

        String qr = "FROM PasswordEntity p where p.usrId = :usrId ORDER BY p.dtCreate DESC";
        Query query = em.createQuery(qr);
        query.setParameter("usrId", usrId);
        query.setFirstResult(0);
        query.setMaxResults(pageSize);
        List<PasswordEntity> list = query.getResultList();
        res.put("page", page);
        res.put("perPage", pageSize);
        res.put("total", countResults);
        res.put("passList", list);
        return res;
    }
}
