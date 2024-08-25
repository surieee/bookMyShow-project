package com.wg.bookmyshow.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.wg.bookmyshow.model.AccountModel;
import com.wg.bookmyshow.service.AccountService;

public class AccountController {

    private final AccountService accountService = new AccountService();
    private final Scanner scanner = new Scanner(System.in);

    public void login() throws ClassNotFoundException, SQLException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        // Input validation
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or password cannot be empty.");
            return;
        }

        // Call service method for login
        AccountModel account = accountService.login(username, password);
        if (account != null) {
            System.out.println("Login successful! Welcome " + account.getName());
            accountService.navigateBasedOnRole(account);
            // Proceed with role-specific actions
        } else {
            System.out.println("Invalid credentials.");
        }
    }

 

	public void createAccount() throws ClassNotFoundException, SQLException {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine().trim();
        System.out.print("Enter role (admin/user/organizer): ");
        String role = scanner.nextLine().trim().toLowerCase();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline

        // Input validation
        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || role.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        // Role validation
        if (!(role.equals("admin") || role.equals("user") || role.equals("organizer"))) {
            System.out.println("Invalid role. Please enter admin, user, or organizer.");
            return;
        }

        // Call service method for creating account
        boolean isCreated = accountService.createAccount(name, username, password, email, phoneNumber, role, age);
        if (isCreated) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Failed to create account. Please try again.");
        }
    }
}
