package com.mich.gwan.shallom.model;

public class LessonQuarter {
    private int quarterId;
    private String quarterYear;
    private String quarterQuarter;

    public LessonQuarter(){}

    public LessonQuarter(int quarterId){
        this.quarterId = quarterId;
    }

    public LessonQuarter(int quarterId, String quarterYear, String quarterQuarter) {
        this.quarterId = quarterId;
        this.quarterYear = quarterYear;
        this.quarterQuarter = quarterQuarter;
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
}
