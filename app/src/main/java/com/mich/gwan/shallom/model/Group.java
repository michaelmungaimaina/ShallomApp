/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 9
 */

package com.mich.gwan.shallom.model;

import android.graphics.Bitmap;

public class Group {
    private String groupAcronym;
    private String groupFullName;
    private String groupDescription;
    private Bitmap groupImage;

    public Group(){}

    public String getGroupAcronym() {
        return groupAcronym;
    }

    public void setGroupAcronym(String groupAcronym) {
        this.groupAcronym = groupAcronym;
    }

    public String getGroupFullName() {
        return groupFullName;
    }

    public void setGroupFullName(String groupFullName) {
        this.groupFullName = groupFullName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Bitmap getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(Bitmap groupImage) {
        this.groupImage = groupImage;
    }
}
