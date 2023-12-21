package com.mich.gwan.shallom.model;

import com.mich.gwan.shallom.enums.Preference;

public class Song {
    private int songId;
    private String songTitle;
    private String songRegDate;
    private String songCategory;
    private String songGroup;
    private String songAuthor;
    private Preference preference;

    public Song(){}

    public Song(int songId, String songTitle, String songRegDate, String songCategory,
                String songGroup, String songAuthor, Preference preference) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songRegDate = songRegDate;
        this.songCategory = songCategory;
        this.songGroup = songGroup;
        this.songAuthor = songAuthor;
        this.preference = preference;
    }

    public Song(int songId){
        this.songId = songId;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongRegDate() {
        return songRegDate;
    }

    public void setSongRegDate(String songRegDate) {
        this.songRegDate = songRegDate;
    }

    public String getSongCategory() {
        return songCategory;
    }

    public void setSongCategory(String songCategory) {
        this.songCategory = songCategory;
    }

    public String getSongGroup() {
        return songGroup;
    }

    public void setSongGroup(String songGroup) {
        this.songGroup = songGroup;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }
}
