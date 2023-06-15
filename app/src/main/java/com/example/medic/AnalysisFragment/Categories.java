package com.example.medic.AnalysisFragment;

import com.google.gson.annotations.SerializedName;

public class Categories {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
