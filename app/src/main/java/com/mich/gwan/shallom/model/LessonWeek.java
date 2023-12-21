package com.mich.gwan.shallom.model;

import com.mich.gwan.shallom.enums.Language;

public class LessonWeek extends LessonQuarter{
    private int lessonWeekId;
    private String lessonTitle;
    private String lessonReading;
    private String memoryVerse;
    private Language lessonLanguage;

    public LessonWeek(){}

    public LessonWeek(int quarterId, int lessonWeekId, String lessonTitle,
                      String lessonReading, String memoryVerse, Language lessonLanguage,
                      String registeredBy, String registrationDate) {
        super(quarterId,registeredBy,registrationDate);
        this.lessonWeekId = lessonWeekId;
        this.lessonTitle = lessonTitle;
        this.lessonReading = lessonReading;
        this.memoryVerse = memoryVerse;
        this.lessonLanguage = lessonLanguage;
    }

    public LessonWeek(int lessonWeekId, Language lessonLanguage, String registeredBy,
                      String registrationDate) {
        super(registeredBy, registrationDate);
        this.lessonWeekId = lessonWeekId;
        this.lessonLanguage = lessonLanguage;
    }

    public int getLessonWeekId() {
        return lessonWeekId;
    }

    public void setLessonWeekId(int lessonWeekId) {
        this.lessonWeekId = lessonWeekId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonReading() {
        return lessonReading;
    }

    public void setLessonReading(String lessonReading) {
        this.lessonReading = lessonReading;
    }

    public String getMemoryVerse() {
        return memoryVerse;
    }

    public void setMemoryVerse(String memoryVerse) {
        this.memoryVerse = memoryVerse;
    }

    public Language getLessonLanguage() {
        return lessonLanguage;
    }

    public void setLessonLanguage(Language lessonLanguage) {
        this.lessonLanguage = lessonLanguage;
    }
}
