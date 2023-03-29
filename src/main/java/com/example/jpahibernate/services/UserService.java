package com.example.jpahibernate.services;

import java.util.Scanner;

public interface UserService {
    void getAll();
    void getById(Long id);
    void getByEmail(String email);
    void save(Scanner scan);
    void update(Long id) throws NoSuchFieldException, IllegalAccessException;
    void deleteById(Long id);
}
