package com.wg.bookmyshow.model;

import java.util.Date;
import java.util.UUID;

import com.wg.bookmyshow.service.AccountService;

public class EventModel {
    private String eventId;
    private String eventName;
    private String venue;
    private double price;
    private Date eventDate;
    private String typeOfEvent; // 'concert', 'conference', etc.
    private String eventDescription;
    private int seatsAvailable;
    private int totalSeats;
    private String organizerId= AccountModel.getAccountId();;
    private int durationHours;
    private int durationMinutes;
	

    // Constructors, getters, and setters
    public EventModel(String string, String string2, double d, java.sql.Date date, String string3, String string4, int i, int j, String string5, int k, int l) {}

    public EventModel( String eventName, String venue, double price, Date eventDate, 
                      String typeOfEvent, String eventDescription, int seatsAvailable, int totalSeats, 
                      int durationHours, int durationMinutes) {
    	this.eventId = UUID.randomUUID().toString(); 
        this.eventName = eventName;
        this.venue = venue;
        this.price = price;
        this.eventDate = eventDate;
        this.typeOfEvent = typeOfEvent;
        this.eventDescription = eventDescription;
        this.seatsAvailable = seatsAvailable;
        this.totalSeats = totalSeats;
        this.durationHours = durationHours;
        this.durationMinutes = durationMinutes;
    }

	@Override
	public String toString() {
		return "EventModel [eventId=" + eventId + ", eventName=" + eventName + ", venue=" + venue + ", price=" + price
				+ ", eventDate=" + eventDate + ", typeOfEvent=" + typeOfEvent + ", eventDescription=" + eventDescription
				+ ", seatsAvailable=" + seatsAvailable + ", totalSeats=" + totalSeats + ", organizerId=" + organizerId
				+ ", durationHours=" + durationHours + ", durationMinutes=" + durationMinutes + "]";
	}
	public String getEventId() {
		return eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getTypeOfEvent() {
		return typeOfEvent;
	}

	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
// orgnaizer id should be fetched internaly and should be equal to account id from login info and remove username then
	public String getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(String organizerId) {
		this.organizerId = AccountModel.getAccountId();
	}

	public int getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(int durationHours) {
		this.durationHours = durationHours;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public void setEventId(String eventId) {
		// TODO Auto-generated method stub
		this.eventId = eventId;
	}

//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return username;
//	}
//
//	public void setUsername(String username) {
//		// TODO Auto-generated method stub
//		this.username = username;
//	}
    // Getters and Setters
    // ...
}

