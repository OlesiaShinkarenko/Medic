package com.example.medic.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnalysisResult {

    @SerializedName("count")
    private String count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private List<Analysis> analyses;

    public List<Analysis> getAnalyses() {
        return analyses;
    }
}
