package com.brs.project.userservice.usergroup.dao;

import com.brs.project.userservice.usergroup.entity.UserGroup;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserGroupDAOImpl implements UserGroupDAO {
    private EntityManager entityManager;

    public UserGroupDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserGroup addUserGrp(UserGroup userGrp) {
        entityManager.persist(userGrp);
        return userGrp;
    }

    @Override
    public UserGroup getUserGroupCodeById(String id) {
        List<Object[]> results;
        UserGroup userGroup = null;
        String qr = "SELECT u.id, u.mtGrpId, um.cd as role, um.name as roleName FROM UserGroup u INNER JOIN UserGroupMtn um ON u.mtGrpId = um.id WHERE u.id = :id";
        Query query = entityManager.createQuery(qr, Object[].class);
        query.setParameter("id", id);
        results = query.getResultList();
        for (Object[] row : results) {
            userGroup = new UserGroup();
            userGroup.setId((String) row[0]);
            userGroup.setMtGrpId((String) row[1]);
            userGroup.setRole((String) row[2]);
            userGroup.setRoleName((String) row[3]);
        }
        return userGroup;
    }
}
