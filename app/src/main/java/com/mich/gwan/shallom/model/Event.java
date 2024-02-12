package com.mich.gwan.shallom.model;

import java.time.LocalDateTime;

public class Event {
    private int eventId;
    private String eventLocation;
    private String eventTitle;
    private String eventDescription;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private LocalDateTime registrationDate;
    private String registeredBy;

    public Event(){}

    public Event(int eventId, String eventLocation, String eventTitle, String eventDescription,
                 LocalDateTime eventStartDate, LocalDateTime eventEndDate, LocalDateTime registrationDate, String registeredBy) {
        this.eventId = eventId;
        this.eventLocation = eventLocation;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.registrationDate = registrationDate;
        this.registeredBy = registeredBy;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDateTime getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(LocalDateTime eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public LocalDateTime getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(LocalDateTime eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }
}
