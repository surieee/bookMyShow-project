package com.wg.bookmyshow.service;

import java.util.List;
import java.util.Scanner;

import com.wg.bookmyshow.controller.BookingController;
import com.wg.bookmyshow.dao.BookingDao;
import com.wg.bookmyshow.model.BookingModel;

public class BookingService {

    private BookingDao bookingDao;

    public BookingService() {
        this.bookingDao = new BookingDao();
    }

    // View all bookings
    public List<BookingModel> viewAllBookings() {
        List<BookingModel> bookings = bookingDao.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (BookingModel booking : bookings) {
                System.out.println(booking);
            }
        }
		return bookings;
    }
  
    
    private void bookTickets() {
        System.out.println("Booking tickets...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Event ID to book: ");
        String eventId = scanner.nextLine().trim();
        System.out.print("Enter number of tickets: ");
        int numberOfTickets = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = false;
		try {
			success = BookingDao.bookTickets(eventId, eventId, numberOfTickets, numberOfTickets, eventId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (success) {
            System.out.println("Tickets booked successfully.");
        } else {
            System.out.println("Failed to book tickets. Please check the event ID or the number of tickets.");
        }
}
}
