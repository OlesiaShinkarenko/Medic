package com.example.medic.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RefreshAccess implements Serializable {
    @SerializedName("refresh")
    private String refresh;
    @SerializedName("access")
    private String access;

    public String getRefresh() {
        return refresh;
    }

    public RefreshAccess() {

    }

    public String getAccess() {
        return access;
    }
}
