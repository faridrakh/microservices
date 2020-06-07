package com.brs.project.userservice.user.dao;

import com.brs.project.userservice.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public UserEntity addUser(UserEntity user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public String updateUser(UserEntity user) {
        entityManager.merge(user);
        return "SUCCESS";
    }

    @Override
    public String deleteUser(String id) {
        UserEntity user = new UserEntity();
        user.setId(id);
        entityManager.remove(user);
        return "SUCCESS";
    }

    @Override
    public UserEntity getUser(String id) {
        UserEntity result;
        String qr = "FROM UserEntity u WHERE u.id = :id";
        Query query = entityManager.createQuery(qr);
        query.setParameter("id", id);
        result = (UserEntity) query.getSingleResult();
        return result;
    }

    @Override
    public UserEntity getUser(String user, String password) {
        UserEntity result;
        String qr = "FROM UserEntity u WHERE u.userName = :userName and u.password = :password";
        Query query = entityManager.createQuery(qr);
        query.setParameter("userName", user);
        query.setParameter("password", password);
        result = (UserEntity) query.getSingleResult();
        return result;
    }

    @Override
    public UserEntity getUserByUserName(String value) {
        UserEntity result = null;
        String qr = "FROM UserEntity u WHERE u.userName = :userName";
        Query query = entityManager.createQuery(qr);
        query.setParameter("userName", value);
        try {
            Object obj = query.getSingleResult();
            if (null != obj){
                result = (UserEntity) obj;
            }
        } catch (NoResultException e) {
            result = null;
        }
        return result;
    }

    @Override
    public List<UserEntity> getUserList() {
        List<Object> result = new ArrayList<>();
        String qr = "FROM UserEntity u";
        Query query = entityManager.createQuery(qr);
        return query.getResultList();
    }

    @Override
    public UserEntity getUserByEmail(String value) {
        UserEntity result = null;
        String qr = "FROM UserEntity u WHERE u.email = :email";
        Query query = entityManager.createQuery(qr);
        query.setParameter("email", value);
        try {
            Object obj = query.getSingleResult();
            if (null != obj){
                result = (UserEntity) obj;
            }
        } catch (NoResultException e) {
            result = null;
        }
        return result;
    }
}
