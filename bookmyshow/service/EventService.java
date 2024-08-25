package com.wg.bookmyshow.service;

import com.wg.bookmyshow.dao.EventDao;
import com.wg.bookmyshow.model.EventModel;

import java.util.List;

public class EventService {

    private final EventDao eventDao;

    public EventService() {
        this.eventDao = new EventDao();
    }

    public boolean createEvent(EventModel event) {
        return eventDao.insertEvent(event);
    }

    public boolean updateEvent(EventModel event) throws ClassNotFoundException {
        return eventDao.updateEvent(event);
    }

    public boolean deleteEvent(String eventId) {
        return eventDao.deleteEvent(eventId);
    }

    public List<EventModel> getAllEvents() {
        return eventDao.getAllEvents();
    }

    public EventModel getEventById(String eventId) {
        return eventDao.getEventById(eventId);
    }

    public EventModel getEventByName(String eventName) throws ClassNotFoundException {
        return eventDao.getEventByName(eventName);
    }
}




