package com.example.medic.AnalysisFragment;

public class DiscountAndNews {

    private Integer id;

    private String name;

    private String price;
    private String description;
    private String image;

    public DiscountAndNews(Integer id,String name, String description, String price, String image) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
