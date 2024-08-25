package com.wg.bookmyshow.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.wg.bookmyshow.model.EventModel;
import com.wg.bookmyshow.service.EventService;

public class EventController {

    private final EventService eventService = new EventService();
    private final Scanner scanner = new Scanner(System.in);

    // Method to create a new event
    public void createEvent() throws SQLException {
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine().trim();
        System.out.print("Enter venue: ");
        String venue = scanner.nextLine().trim();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume the remaining newline
        System.out.print("Enter event date (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine().trim();
        System.out.print("Enter type of event (concert/conference/meetup/workshop/stand-up comedy/seminar): ");
        String typeOfEvent = scanner.nextLine().trim();
        System.out.print("Enter event description: ");
        String eventDescription = scanner.nextLine().trim();
        System.out.print("Enter number of seats available: ");
        int seatsAvailable = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline
        System.out.print("Enter total number of seats: ");
        int totalSeats = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline
//        System.out.print("Enter organizer username: ");
//        String organizerId = scanner.nextLine().trim();
        System.out.print("Enter duration in hours: ");
        int durationHours = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline
        System.out.print("Enter duration in minutes: ");
        int durationMinutes = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date eventDate = formatter.parse(dateInput);
            EventModel event = new EventModel(eventName, venue, price, eventDate, typeOfEvent, 
                                              eventDescription, seatsAvailable, totalSeats,
                                              durationHours, durationMinutes);

            if (eventService.createEvent(event)) {
                System.out.println("Event created successfully.");
            } else {
                System.out.println("Failed to create event.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
//    public void updateEvent() throws SQLException {
//        System.out.print("Enter Event ID to update: ");
//        String eventId = scanner.nextLine().trim();  // Ensure to fetch the correct event by its ID
//
//        // Same input prompts for event details as before
//        // ...
//        // After getting all inputs, create a new EventModel
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date eventDate = formatter.parse(dateInput);
//            EventModel event = new EventModel(eventId, eventName, venue, price, eventDate, typeOfEvent, 
//                                              eventDescription, seatsAvailable, totalSeats,
//                                              durationHours, durationMinutes);
//
//            // Fetch the current organizer ID from the logged-in account
//            String organizerId = // Fetch from session or user input
//            event.setOrganizerId(organizerId);
//
//            if (eventService.updateEvent(event)) {
//                System.out.println("Event updated successfully.");
//            } else {
//                System.out.println("Failed to update event.");
//            }
//        } catch (ParseException e) {
//            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
//        }
//    }

    public void updateEvent() throws SQLException, ClassNotFoundException {
        System.out.print("Enter the name of the event to update: ");
        String eventName = scanner.nextLine().trim();

        EventModel event = eventService.getEventByName(eventName);
        if (event == null) {
            System.out.println("Event not found. Please check the event name and try again.");
            return;
        }

        boolean updating = true;
        while (updating) {
            System.out.println("\nWhich field would you like to update?");
            System.out.println("1. Venue");
            System.out.println("2. Price");
            System.out.println("3. Date");
            System.out.println("4. Type of Event");
            System.out.println("5. Description");
            System.out.println("6. Seats Available");
            System.out.println("7. Total Seats");
            System.out.println("8. Duration (Hours)");
            System.out.println("9. Duration (Minutes)");
            System.out.println("0. Exit");
            System.out.print("Select an option (0-9): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the remaining newline

            switch (choice) {
                case 1:
                    System.out.print("Update venue: ");
                    String venue = scanner.nextLine().trim();
                    event.setVenue(venue);
                    break;
                case 2:
                    System.out.print("Update price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // consume the remaining newline
                    event.setPrice(price);
                    break;
                case 3:
                    System.out.print("Update event date (yyyy-MM-dd): ");
                    String dateInput = scanner.nextLine().trim();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date eventDate = formatter.parse(dateInput);
                        event.setEventDate(eventDate);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                    break;
                case 4:
                    System.out.print("Update type of event: ");
                    String typeOfEvent = scanner.nextLine().trim();
                    event.setTypeOfEvent(typeOfEvent);
                    break;
                case 5:
                    System.out.print("Update event description: ");
                    String eventDescription = scanner.nextLine().trim();
                    event.setEventDescription(eventDescription);
                    break;
                case 6:
                    System.out.print("Update number of seats available: ");
                    int seatsAvailable = scanner.nextInt();
                    scanner.nextLine(); // consume the remaining newline
                    event.setSeatsAvailable(seatsAvailable);
                    break;
                case 7:
                    System.out.print("Update total number of seats: ");
                    int totalSeats = scanner.nextInt();
                    scanner.nextLine(); // consume the remaining newline
                    event.setTotalSeats(totalSeats);
                    break;
                case 8:
                    System.out.print("Update duration in hours: ");
                    int durationHours = scanner.nextInt();
                    scanner.nextLine(); // consume the remaining newline
                    event.setDurationHours(durationHours);
                    break;
                case 9:
                    System.out.print("Update duration in minutes: ");
                    int durationMinutes = scanner.nextInt();
                    scanner.nextLine(); // consume the remaining newline
                    event.setDurationMinutes(durationMinutes);
                    break;
                case 0:
                    updating = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }

            if (updating) {
                // Ask if the user wants to continue updating other fields
                System.out.print("Do you want to update another field? (yes/no): ");
                String continueChoice = scanner.nextLine().trim().toLowerCase();
                if (!continueChoice.equals("yes")) {
                    updating = false;
                }
            }
        }

        // After all updates are done, save the updated event
        if (eventService.updateEvent(event)) {
            System.out.println("Event updated successfully.");
        } else {
            System.out.println("Failed to update event.");
        }
    }

 

    public void viewEvents() {
        System.out.println("Viewing events...");
        List<EventModel> events = eventService.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (EventModel event : events) {
                System.out.println("Event ID: " + event.getEventId());
                System.out.println("Event Name: " + event.getEventName());
                System.out.println("Venue: " + event.getVenue());
                System.out.println("Date: " + event.getEventDate());
                System.out.println("Price: " + event.getPrice());
                System.out.println("---------------------------");
            }
        }
    }
}


