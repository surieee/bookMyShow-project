package com.wg.bookmyshow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wg.bookmyshow.config.DBConnection;
import com.wg.bookmyshow.model.BookingModel;

public class BookingDao {

    // Method to get a list of all bookings
    public List<BookingModel> getAllBookings() {
        String query = "SELECT * FROM Booking";
        List<BookingModel> bookings = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BookingModel booking = new BookingModel();
                booking.setBookingId(rs.getString("booking_id"));
                booking.setBookingDate(rs.getTimestamp("booking_date"));
                booking.setTotalAmount(rs.getDouble("total_amount"));
                booking.setBookingStatus(rs.getString("booking_status"));
                booking.setUserId(rs.getString("account_id"));
                booking.setEventId(rs.getString("event_id"));
                booking.setPaymentId(rs.getString("payment_id"));
                // Assuming ticketIds is a serialized list/array
                booking.setTicketIds(rs.getString("ticket_ids").split(","));

                bookings.add(booking);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Method to book tickets
    public static boolean bookTickets(String accountId, String eventId, int numberOfTickets, double totalAmount, String paymentId) {
        String bookingId = UUID.randomUUID().toString();
        String bookingStatus = "CONFIRMED";
        String insertBookingQuery = "INSERT INTO Booking (booking_id, booking_date, total_amount, booking_status, account_id, event_id, payment_id, ticket_ids) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement bookingStmt = connection.prepareStatement(insertBookingQuery)) {

            // Generate ticket IDs
            List<String> ticketIds = new ArrayList<>();
            for (int i = 0; i < numberOfTickets; i++) {
                ticketIds.add(UUID.randomUUID().toString());
            }
            String ticketIdsString = String.join(",", ticketIds);

            // Set parameters for the booking
            bookingStmt.setString(1, bookingId);
            bookingStmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            bookingStmt.setDouble(3, totalAmount);
            bookingStmt.setString(4, bookingStatus);
            bookingStmt.setString(5, accountId);
            bookingStmt.setString(6, eventId);
            bookingStmt.setString(7, paymentId);
            bookingStmt.setString(8, ticketIdsString);

            // Execute the booking insertion
            int rowsInserted = bookingStmt.executeUpdate();

            if (rowsInserted > 0) {
                // Optionally: update event ticket inventory or other related operations
                System.out.println("Booking created successfully with Booking ID: " + bookingId);
                return true;
            } else {
                System.out.println("Failed to create booking.");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

