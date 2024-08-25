package com.wg.bookmyshow.app;

import java.util.Scanner;

public class AdminMenu {
    public void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Accounts");
            System.out.println("2. Manage Events");
            System.out.println("3. View Reports");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageAccounts();
                    break;
                case 2:
                    manageEvents();
                    break;
                case 3:
                    viewReports();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return; // Exit to the previous menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageAccounts() {
        System.out.println("Managing accounts...");
        // Implementation for managing accounts
    }

    private void manageEvents() {
        System.out.println("Managing events...");
        // Implementation for managing events
    }

    private void viewReports() {
        System.out.println("Viewing reports...");
        // Implementation for viewing reports
    }
}

