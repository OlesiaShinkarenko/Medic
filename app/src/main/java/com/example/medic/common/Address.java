package com.example.medic.common;

public class Address {
    private  String add;
    private String flat;
    private String entrance;
    private String floor;
    private String doorphone;
    private String label;
    private String longitude;
    private String height;
    private String width;

    public Address(String add, String flat, String entrance, String floor, String doorphone, String label, String longitude, String height, String width) {
        this.add = add;
        this.flat = flat;
        this.entrance = entrance;
        this.floor = floor;
        this.doorphone = doorphone;
        this.label = label;
        this.longitude = longitude;
        this.height = height;
        this.width = width;
    }


    public String getAdd() {
        return add;
    }

    public String getFlat() {
        return flat;
    }

    public String getEntrance() {
        return entrance;
    }

    public String getFloor() {
        return floor;
    }

    public String getDoorphone() {
        return doorphone;
    }

    public String getLabel() {
        return label;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }
}
