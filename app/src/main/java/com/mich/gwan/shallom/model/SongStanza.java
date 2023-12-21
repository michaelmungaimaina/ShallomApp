package com.mich.gwan.shallom.model;

public class SongStanza extends Song{
    private int stanzaId;
    private String stanzaContent;

    public SongStanza(){}

    public SongStanza(int songId, int stanzaId, String stanzaContent) {
        super(songId);
        this.stanzaId = stanzaId;
        this.stanzaContent = stanzaContent;
    }

    public int getStanzaId() {
        return stanzaId;
    }

    public void setStanzaId(int stanzaId) {
        this.stanzaId = stanzaId;
    }

    public String getStanzaContent() {
        return stanzaContent;
    }

    public void setStanzaContent(String stanzaContent) {
        this.stanzaContent = stanzaContent;
    }
}
