package com.wg.bookmyshow.service;

import java.sql.SQLException;

import com.wg.bookmyshow.app.AdminMenu;
import com.wg.bookmyshow.app.OrganizerMenu;
import com.wg.bookmyshow.app.UserMenu;
import com.wg.bookmyshow.dao.AccountDao;
import com.wg.bookmyshow.model.AccountModel;

public class AccountService {

    private final AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDao();
    }

    public boolean createAccount(String name, String username, String password, String email, String phoneNumber, String role, int age) throws SQLException, ClassNotFoundException {
        // Validate input fields
        if (name == null || name.trim().isEmpty() ||
            username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            phoneNumber == null || phoneNumber.trim().isEmpty() ||
            role == null || role.trim().isEmpty() ||
            age <= 0) {
            System.err.println("Invalid input. Please provide valid account details.");
            return false;
        }

        // Create AccountModel object
        AccountModel account = new AccountModel();
        account.setName(name);
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);
        account.setPhoneNumber(phoneNumber);
        account.setRole(role);
        account.setAge(age);

        try {
            accountDao.createAccount(account);
            return true;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // To get more details about the error
            return false;
        }
    }

    public AccountModel login(String username, String password) throws ClassNotFoundException, SQLException {
        // Validate input fields
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            System.err.println("Username and password cannot be empty.");
            return null;
        }

        try {
            AccountModel account = accountDao.findByUsername(username);
            if (account != null) {
                String hashedPassword = password; // Ensure this method hashes the password
                if (account.getPassword().equals(hashedPassword)) {
                    // Set organizerId if the role is organizer
                    if ("organizer".equals(account.getRole())) {
                        account.setOrganizerId(account.getAccountId());
                    }
                    return account;
                }
            }
            System.err.println("Invalid username or password.");
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // To get more details about the error
            return null;
        }
    }

    public void navigateBasedOnRole(AccountModel account) throws SQLException, ClassNotFoundException {
        String role = account.getRole();
        switch (role) {
            case "admin":
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.showAdminMenu();
                break;
            case "user":
                UserMenu userMenu = new UserMenu();
                userMenu.showUserMenu();
                break;
            case "organizer":
                OrganizerMenu organizerMenu = new OrganizerMenu();
                organizerMenu.showOrganizerMenu();
                break;
            default:
                System.out.println("Unknown role. Access denied.");
        }
    }

    // Update an existing account
    public void updateAccount(AccountModel account) {
        if (account == null || AccountModel.getAccountId() == null || account.getAccountId().trim().isEmpty()) {
            System.err.println("Invalid account details. Cannot update account.");
            return;
        }

        try {
            boolean success = accountDao.updateAccount(account);
            if (success) {
                System.out.println("Account updated successfully.");
            } else {
                System.out.println("Failed to update account.");
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while updating the account: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete an account by ID
    public void deleteAccount(String accountId) {
        if (accountId == null || accountId.trim().isEmpty()) {
            System.err.println("Invalid account ID. Cannot delete account.");
            return;
        }

        try {
            boolean success = accountDao.deleteAccount(accountId);
            if (success) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Failed to delete account.");
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while deleting the account: " + e.getMessage());
            e.printStackTrace();
        }
    }
}





