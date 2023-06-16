package com.example.medic.AnalysisFragment;

import com.google.gson.annotations.SerializedName;

public class Categories {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
