package com.example.medic.common;

import com.google.gson.annotations.SerializedName;

public class Refresh {
    @SerializedName("refresh")
    private String refresh;

    public Refresh(String refresh) {
        this.refresh = refresh;
    }
}
