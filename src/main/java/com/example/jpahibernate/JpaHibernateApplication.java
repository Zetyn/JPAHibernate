package com.example.jpahibernate;

import com.example.jpahibernate.services.UserService;
import com.example.jpahibernate.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        do {
            String command = scan.next();
            switch (command) {
                case "/getAll" -> {
                    userService.getAll();
                    scan.nextLine();
                }
                case "/getById" -> {
                    System.out.println("Id: ");
                    long id = scan.nextLong();
                    userService.getById(id);
                    scan.nextLine();
                }
                case "/getByEmail" -> {
                    System.out.println("Email: ");
                    String email = scan.next();
                    userService.getByEmail(email);
                    scan.nextLine();
                }
                case "/add" -> {
                    userService.save(scan);
                    scan.nextLine();
                }
                case "/update" -> {
                    System.out.println("Id: ");
                    userService.update(scan.nextLong());
                    scan.nextLine();
                }
                case "/delete" -> {
                    System.out.println("Id: ");
                    long id = scan.nextLong();
                    userService.deleteById(id);
                    scan.nextLine();
                }
                case "/exit" -> System.exit(0);
                default -> System.out.println("Unknown command");
            }

        } while (true);
    }
}
