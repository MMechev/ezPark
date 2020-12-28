package com.example.ezpark;

public class City {

    private String name;
    private String info;
    private int num_parking_lots;
    private int img_id;

    public City(String cityName, String cityDescription, int numberOfParkingLots, int imageResourceId) {
        this.name = cityName;
        this.info = cityDescription ;
        this.num_parking_lots = numberOfParkingLots;
        this.img_id= imageResourceId;
    }

    public String getCityName() {
        return name;
    }

    public String getDescription() {
        return info + num_parking_lots;
    }

    public int getNumberOfParkingLots() {
        return num_parking_lots;
    }

    public int getImageResourceId() {
        return img_id;
    }
}
