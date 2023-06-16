package com.example.medic.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResult {
    @SerializedName("count")
    private int count;

    @SerializedName("next")
    private String next;

    @SerializedName("previous")
    private String previous;

    @SerializedName("results")
    List<DiscountAndNews> results;

    public List<DiscountAndNews> getResults() {
        return results;
    }
}
