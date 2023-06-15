package com.example.medic.AnalysisFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResult {
    @SerializedName("count")
    private String count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private List<Categories> categories;

    public List<Categories> getCategories() {
        return categories;
    }
}
