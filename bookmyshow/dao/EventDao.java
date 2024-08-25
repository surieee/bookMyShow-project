package com.wg.bookmyshow.dao;

import com.wg.bookmyshow.config.DBConnection;
import com.wg.bookmyshow.model.EventModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    // Method to insert a new event
    public boolean insertEvent(EventModel event) {
        String query = "INSERT INTO Event (event_id, event_name, venue, price, event_date, type_of_event, event_description, seats_available, total_seats, organizer_id, duration_hours, duration_minutes) " +
                       "VALUES (  ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

        	stmt.setString(1, event.getEventId());
            stmt.setString(2, event.getEventName());
            stmt.setString(3, event.getVenue());
            stmt.setDouble(4, event.getPrice());
            stmt.setDate(5, new java.sql.Date(event.getEventDate().getTime()));
            stmt.setString(6, event.getTypeOfEvent());
            stmt.setString(7, event.getEventDescription());
            stmt.setInt(8, event.getSeatsAvailable());
            stmt.setInt(9, event.getTotalSeats());
            stmt.setString(10, event.getOrganizerId());
            stmt.setInt(11, event.getDurationHours());
            stmt.setInt(12, event.getDurationMinutes());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
  

    // Method to update an existing event
//    public boolean updateEvent(EventModel event) {
//        String query = "UPDATE Event SET event_name = ?, venue = ?, price = ?, event_date = ?, type_of_event = ?, event_description = ?, seats_available = ?, total_seats = ?, organizer_id = ?, duration_hours = ?, duration_minutes = ? " +
//        		 "WHERE event_id = ?";
//        try (Connection connection = DBConnection.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(query)) {
//
//
//            stmt.setString(1, event.getEventName());
//            stmt.setString(2, event.getVenue());
//            stmt.setDouble(3, event.getPrice());
//            stmt.setDate(4, new java.sql.Date(event.getEventDate().getTime()));
//            stmt.setString(5, event.getTypeOfEvent());
//            stmt.setString(6, event.getEventDescription());
//            stmt.setInt(7, event.getSeatsAvailable());
//            stmt.setInt(8, event.getTotalSeats());
//            stmt.setInt(9, event.getDurationHours());
//            stmt.setInt(10, event.getDurationMinutes());
//            stmt.setString(11, event.getEventId());   // Correct order: set event_id
//            stmt.setString(12, event.getOrganizerId()); 
//          
//
//            int rowsAffected = stmt.executeUpdate();
//            return rowsAffected > 0;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    public EventModel getEventByName(String eventName) throws ClassNotFoundException {
        EventModel event = null;
        String query = "SELECT * FROM Event WHERE event_name = ?";
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, eventName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                event = new EventModel(query, query, 0, null, query, query, 0, 0, 0, 0);
                event.setEventId(resultSet.getString("event_id"));
                event.setEventName(resultSet.getString("event_name"));
                event.setVenue(resultSet.getString("venue"));
                event.setPrice(resultSet.getDouble("price"));
                event.setEventDate(resultSet.getDate("event_date"));
                event.setTypeOfEvent(resultSet.getString("type_of_event"));
                event.setEventDescription(resultSet.getString("event_description"));
                event.setSeatsAvailable(resultSet.getInt("seats_available"));
                event.setTotalSeats(resultSet.getInt("total_seats"));
                event.setDurationHours(resultSet.getInt("duration_hours"));
                event.setDurationMinutes(resultSet.getInt("duration_minutes"));
                event.setOrganizerId(resultSet.getString("organizer_id")); // Assuming you have this field
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    public boolean updateEvent(EventModel event) throws ClassNotFoundException {
        String query = "UPDATE Event SET event_name = ?, venue = ?, price = ?, event_date = ?, type_of_event = ?, event_description = ?, seats_available = ?, total_seats = ?, organizer_id = ?, duration_hours = ?, duration_minutes = ? " +
                       "WHERE event_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, event.getEventName());
            stmt.setString(2, event.getVenue());
            stmt.setDouble(3, event.getPrice());
            stmt.setDate(4, new java.sql.Date(event.getEventDate().getTime()));
            stmt.setString(5, event.getTypeOfEvent());
            stmt.setString(6, event.getEventDescription());
            stmt.setInt(7, event.getSeatsAvailable());
            stmt.setInt(8, event.getTotalSeats());
            stmt.setString(9, event.getOrganizerId());
            stmt.setInt(10, event.getDurationHours());
            stmt.setInt(11, event.getDurationMinutes());
            stmt.setString(12, event.getEventId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete an event by ID
    public boolean deleteEvent(String eventId) {
        String query = "DELETE FROM Event WHERE event_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, eventId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve an event by ID
    public EventModel getEventById(String eventId) {
        String query = "SELECT * FROM Event WHERE event_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, eventId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToEvent(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to retrieve all events
    public List<EventModel> getAllEvents() {
        List<EventModel> events = new ArrayList<>();
        String query = "SELECT * FROM Event";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    private EventModel mapResultSetToEvent(ResultSet rs) throws SQLException {
        return new EventModel(
            rs.getString("event_name"),
            rs.getString("venue"),
            rs.getDouble("price"),
            rs.getDate("event_date"),
            rs.getString("type_of_event"),
            rs.getString("event_description"),
            rs.getInt("tickets_available"),
            rs.getInt("total_seats"),
            rs.getString("organizer_id"),
            rs.getInt("duration_hours"),
            rs.getInt("duration_minutes")
        );
    }
}

