package com.example.jpahibernate.services.impl;

import com.example.jpahibernate.models.User;
import com.example.jpahibernate.repositories.UserRepo;
import com.example.jpahibernate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public void getAll() {
        List<User> list = userRepo.getAll();
        if (list == null || list.size() == 0)
            System.out.println("No users in the list");
        else
            list.forEach(System.out::println);

    }

    @Override
    public void getById(Long id) {
        User user = userRepo.getById(id);
        if (user != null)
            System.out.println(user);
        else
            System.out.println("User not founded");
    }

    @Override
    public void getByEmail(String email) {
        List<User> list = userRepo.getByEmail(email);
        if (list != null)
            list.forEach(System.out::println);
        else
            System.out.println("User not founded");
    }

    @Override
    public void save(Scanner scan) {
        System.out.print("First name: ");
        String firstName = scan.next();

        System.out.print("Last name: ");
        String lastName = scan.next();

        System.out.print("Age: ");
        int age = scan.nextInt();

        System.out.print("Email: ");
        String email = scan.next();

        userRepo.upsert(User.builder().firstName(firstName).lastName(lastName).email(email).age(age).build());
        System.out.println("User successfully added");
    }

    @Override
    public void update(Long id) throws NoSuchFieldException, IllegalAccessException {
        User user = userRepo.getById(id);
        Scanner scan = new Scanner(System.in);
        System.out.println("Field name: ");
        String fieldName = scan.nextLine();
        System.out.println("Field value: ");
        String fieldValue = scan.nextLine();
        if (user != null) {
            try {
                Field field = User.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(user, fieldValue);
                userRepo.upsert(user);
                System.out.println("User successfully updated");
            } catch (NoSuchFieldException e) {
                System.out.println("Field in User NOT found!");
            }
        } else System.out.println("No user found");
    }

    @Override
    public void deleteById(Long id) {
        if (userRepo.deleteById(id))
            System.out.println("User deleted successfully");
        else
            System.out.println("No such user found");
    }
}
