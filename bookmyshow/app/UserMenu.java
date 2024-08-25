package com.wg.bookmyshow.app;

import java.util.Scanner;

import com.wg.bookmyshow.controller.AccountController;
import com.wg.bookmyshow.controller.BookingController;
import com.wg.bookmyshow.controller.EventController;

public class UserMenu {
    public void showUserMenu() {
        Scanner scanner = new Scanner(System.in);
        final EventController eventController = new EventController();
        final BookingController bookingController = new BookingController();


        while (true) {
            System.out.println("User Menu:");
            System.out.println("1. View Events");
            System.out.println("2. Book Tickets");
            System.out.println("3. View Booking History");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    eventController.viewEvents();
                    break;
                case 2:
                    bookingController.bookTickets();
                    break;
                case 3:
                    bookingController.viewBookingHistory();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return; // Exit to the previous menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
