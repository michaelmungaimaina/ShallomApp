package com.mich.gwan.shallom.model;

public class LessonCategory extends LessonQuarter{
    private int lessonCategoryId;
    private String lessonTitle;
    private String lessonReading;
    private String memoryVerse;
    private String lessonLanguage;

    public LessonCategory(){}

    public LessonCategory(int quarterId, int lessonCategoryId, String lessonTitle,
                          String lessonReading, String memoryVerse, String lessonLanguage) {
        super(quarterId);
        this.lessonCategoryId = lessonCategoryId;
        this.lessonTitle = lessonTitle;
        this.lessonReading = lessonReading;
        this.memoryVerse = memoryVerse;
        this.lessonLanguage = lessonLanguage;
    }

    public LessonCategory(int lessonCategoryId, String lessonLanguage) {
        this.lessonCategoryId = lessonCategoryId;
        this.lessonLanguage = lessonLanguage;
    }

    public int getLessonCategoryId() {
        return lessonCategoryId;
    }

    public void setLessonCategoryId(int lessonCategoryId) {
        this.lessonCategoryId = lessonCategoryId;
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

    public String getLessonLanguage() {
        return lessonLanguage;
    }

    public void setLessonLanguage(String lessonLanguage) {
        this.lessonLanguage = lessonLanguage;
    }
}
