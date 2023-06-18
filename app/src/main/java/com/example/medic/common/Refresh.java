package com.example.medic.common;

import com.google.gson.annotations.SerializedName;

public class Refresh {
    @SerializedName("refresh")
    private String refresh;
    @SerializedName("access")
    private String access;

    public String getRefresh() {
        return refresh;
    }

    public Refresh() {

    }

    public String getAccess() {
        return access;
    }
}
