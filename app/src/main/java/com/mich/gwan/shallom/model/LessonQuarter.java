package com.mich.gwan.shallom.model;

public class LessonQuarter {
    private int quarterId;
    private String quarterYear;
    private String quarterQuarter;
    private String registrationDate;
    private String registeredBy;

    public LessonQuarter(){}

    public LessonQuarter(int quarterId, String registeredBy, String registrationDate){
        this.quarterId = quarterId;
        this.registeredBy = registeredBy;
        this.registrationDate = registrationDate;
    }

    public LessonQuarter(int quarterId, String quarterYear, String quarterQuarter,
                         String registeredBy, String registrationDate) {
        this.quarterId = quarterId;
        this.quarterYear = quarterYear;
        this.quarterQuarter = quarterQuarter;
        this.registeredBy = registeredBy;
        this.registrationDate = registrationDate;
    }

    public LessonQuarter(String registeredBy, String registrationDate){
        this.registrationDate = registrationDate;
        this.registeredBy = registeredBy;
    }

    public int getQuarterId() {
        return quarterId;
    }

    public void setQuarterId(int quarterId) {
        this.quarterId = quarterId;
    }

    public String getQuarterYear() {
        return quarterYear;
    }

    public void setQuarterYear(String quarterYear) {
        this.quarterYear = quarterYear;
    }

    public String getQuarterQuarter() {
        return quarterQuarter;
    }

    public void setQuarterQuarter(String quarterQuarter) {
        this.quarterQuarter = quarterQuarter;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }
}
