package com.example.medic.common;

import com.google.gson.annotations.SerializedName;

public class CardPatient {
    @SerializedName("id")
    private Integer id;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("middle_name")
    private String middle_name;
    @SerializedName("date_of_birth")
    private String date_of_birth;
    @SerializedName("pol")
    private String pol;
    @SerializedName("image")
    private String image;

    public CardPatient(String first_name, String last_name, String middle_name, String date_of_birth, String pol) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.date_of_birth = date_of_birth;
        this.pol = pol;
    }


    public CardPatient(Integer id, String first_name, String last_name, String middle_name, String date_of_birth, String pol, String image) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.date_of_birth = date_of_birth;
        this.pol = pol;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getPol() {
        return pol;
    }

    public String getImage() {
        return image;
    }
}
