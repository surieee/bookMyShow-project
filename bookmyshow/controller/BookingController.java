package com.wg.bookmyshow.controller;

import java.util.List;

import com.wg.bookmyshow.model.BookingModel;
import com.wg.bookmyshow.service.BookingService;

public class BookingController {
    private BookingService bookingService;

    public BookingController() {
        this.bookingService = new BookingService();
    }

    public List<BookingModel> viewBookingHistory() {
        return bookingService.viewAllBookings();
    }

	public static boolean bookTickets() {
		return false;
		// TODO Auto-generated method stub
		
	}

	public void updateEvent() {
		// TODO Auto-generated method stub
		
	}

	public void manageBookings() {
		// TODO Auto-generated method stub
		
	}


	
}
