package com.example.medic.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfilesModelResponse {
    @SerializedName("count")
    private int count;

    @SerializedName("next")
    private String next;

    @SerializedName("previous")
    private String previous;

    @SerializedName("results")
    List<CardPatient> results;

    public List<CardPatient> getResults() {
        return results;
    }
}
