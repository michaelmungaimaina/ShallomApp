package com.mich.gwan.shallom.model;

public class Event {
    private int eventId;
    private String eventLocation;
    private String eventTitle;
    private String eventDescription;
    private String eventStartDate;
    private String eventEndDate;

    public Event(){}

    public Event(int eventId, String eventLocation, String eventTitle, String eventDescription, String eventStartDate, String eventEndDate) {
        this.eventId = eventId;
        this.eventLocation = eventLocation;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
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

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }
}
