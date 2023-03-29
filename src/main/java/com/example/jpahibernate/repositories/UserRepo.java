package com.example.jpahibernate.repositories;

import com.example.jpahibernate.models.User;

import java.util.List;

public interface UserRepo {
    List<User> getAll();
    User getById(Long id);
    List<User> getByEmail(String email);
    User upsert(User u);
    boolean deleteById(Long id);
}
