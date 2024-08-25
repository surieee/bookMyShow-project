package com.wg.bookmyshow.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.wg.bookmyshow.controller.AccountController;

public class AccountMenu {

	public AccountMenu() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
	     Scanner scanner = new Scanner(System.in);
	        AccountController accountController = new AccountController();

	        while (true) {
	            System.out.println("1. Create Account");
	            System.out.println("2. Login");
	            System.out.println("3. Exit");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();  // Consume newline

	            switch (choice) {
	                case 1:
	                    accountController.createAccount();
	                    break;
	                case 2:
	                    accountController.login();
	                    break;
	                case 3:
	                    System.out.println("Exiting...");
	                    System.exit(0);
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	}
	}}

