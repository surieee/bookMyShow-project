package com.wg.bookmyshow.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.wg.bookmyshow.controller.BookingController;
import com.wg.bookmyshow.controller.EventController;

public class OrganizerMenu {
    public void showOrganizerMenu() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        final EventController eventController = new EventController();
        final BookingController bookingController = new BookingController();

        while (true) {
            System.out.println("Organizer Menu:");
            System.out.println("1. Create Event");
            System.out.println("2. Update Event");
            System.out.println("3. Manage Bookings");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    eventController.createEvent();
                    break;
                case 2:
                    eventController.updateEvent();
                    break;
                case 3:
                    bookingController.manageBookings();
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
