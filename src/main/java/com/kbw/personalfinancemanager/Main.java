package com.kbw.personalfinancemanager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Personal Finance Manager!");
        System.out.println("1. Register");
        System.out.println("2. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (choice == 1) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User newUser = new User(name, email, password);
            boolean isRegistered = userService.registerUser(newUser);

            if (isRegistered) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed!");
            }
        }

        scanner.close();
    }
}
