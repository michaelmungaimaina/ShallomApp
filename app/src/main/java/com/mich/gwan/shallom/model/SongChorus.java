package com.mich.gwan.shallom.model;

public class SongChorus extends Song{
    private int chorusId;
    private String chorusContent;

    public SongChorus(){}

    public SongChorus(int songId, int chorusId, String chorusContent) {
        super(songId);
        this.chorusId = chorusId;
        this.chorusContent = chorusContent;
    }

    public int getChorusId() {
        return chorusId;
    }

    public void setChorusId(int chorusId) {
        this.chorusId = chorusId;
    }

    public String getChorusContent() {
        return chorusContent;
    }

    public void setChorusContent(String chorusContent) {
        this.chorusContent = chorusContent;
    }
}
