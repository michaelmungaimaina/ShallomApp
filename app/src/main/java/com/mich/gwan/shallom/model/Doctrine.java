package com.mich.gwan.shallom.model;

public class Doctrine {
    private int doctrineId;
    private String doctrineContent;
    private String doctrineRef;

    public Doctrine(){}

    public Doctrine(int doctrineId, String doctrineContent, String doctrineRef) {
        this.doctrineId = doctrineId;
        this.doctrineContent = doctrineContent;
        this.doctrineRef = doctrineRef;
    }

    public int getDoctrineId() {
        return doctrineId;
    }

    public void setDoctrineId(int doctrineId) {
        this.doctrineId = doctrineId;
    }

    public String getDoctrineContent() {
        return doctrineContent;
    }

    public void setDoctrineContent(String doctrineContent) {
        this.doctrineContent = doctrineContent;
    }

    public String getDoctrineRef() {
        return doctrineRef;
    }

    public void setDoctrineRef(String doctrineRef) {
        this.doctrineRef = doctrineRef;
    }
}
