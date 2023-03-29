package com.example.jpahibernate.repositories;


import com.example.jpahibernate.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImpl {
    @Autowired
    private EntityManager entityManager;
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    public User getById(Long id) {
        return entityManager.find(User.class,id);
    }

    public List<User> getByEmail(String email) {
        Query query = entityManager.createQuery("select u from User u where u.email=:email");
        query.setParameter("email",email);
        return query.getResultList();
    }

    @Transactional
    public User upsert(User u) {
        return entityManager.merge(u);
    }

    @Transactional
    public boolean deleteById(Long id) {
        User u = getById(id);
        if (u != null) {
            entityManager.remove(u);
            return true;
        } else return false;
    }
}
