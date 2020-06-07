package com.brs.project.userservice.usergroup.dao;

import com.brs.project.userservice.usergroup.entity.UserGroupMtn;
import com.sytan.base.lib.ApplicationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserGroupMtnDAOImpl implements UserGroupMtnDAO {
    private EntityManager entityManager;

    public UserGroupMtnDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<UserGroupMtn> getUserGroupMtnList() throws ApplicationException {
        String qr = "FROM UserGroupMtn";
        Query query = entityManager.createQuery(qr);
        return query.getResultList();
    }

    @Override
    public String getUserGroupMtnByCd(String cd) throws ApplicationException {
        String qr = "SELECT id FROM UserGroupMtn mtn WHERE mtn.cd = :cd";
        Query query = entityManager.createQuery(qr);
        query.setParameter("cd", cd);
        return query.getResultList().get(0).toString();
    }
}
