package com.example.medic.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Analysis implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private String price;
    @SerializedName("category")
    private Integer category;
    @SerializedName("time_result")
    private String time_result;
    @SerializedName("preparation")
    private String preparation;
    @SerializedName("bio")
    private String bio;


    public Analysis(Integer id, String name, String description, String price, Integer category, String time_result, String preparation, String bio) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.time_result = time_result;
        this.preparation = preparation;
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPrice(){
        return price;
    }
    public String getPriceFormat() {
        double priceDouble = Double.parseDouble(price);
        int priceInt = (int) priceDouble;
        String javaFormatString  = "%d ₽";
        String  substitutedString  =  String.format(javaFormatString, priceInt);
        return substitutedString;
    }
    public int getPrice_int() {
        double priceDouble = Double.parseDouble(price);
        int priceInt = (int) priceDouble;
        return priceInt;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTime_result() {
        return time_result;
    }

    public void setTime_result(String time_result) {
        this.time_result = time_result;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
