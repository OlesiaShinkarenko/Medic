package com.example.medic.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnalysisInPatient implements Serializable {
    @SerializedName("analysis")
    private Integer analysis;
}
