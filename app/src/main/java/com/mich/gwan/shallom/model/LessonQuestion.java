package com.mich.gwan.shallom.model;

public class LessonQuestion extends LessonCategory{
    private int questionId;
    private String question;
    private String questionRefBooks;
    private String explanation;

    public LessonQuestion(){}

    public LessonQuestion(int lessonCategoryId, String lessonLanguage, int questionId,
                          String question, String questionRefBooks, String explanation) {
        super(lessonCategoryId, lessonLanguage);
        this.questionId = questionId;
        this.question = question;
        this.questionRefBooks = questionRefBooks;
        this.explanation = explanation;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionRefBooks() {
        return questionRefBooks;
    }

    public void setQuestionRefBooks(String questionRefBooks) {
        this.questionRefBooks = questionRefBooks;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
