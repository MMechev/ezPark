package com.example.ezpark;

public class Parking {

    private int id;
    private String name;
    private String city;
    private int num_spaces;
    private double longitude;
    private double latitude;

    public Parking(){

    }

    public Parking(String name, String city, int num_spaces, double longitude, double latitude){
        this.name = name;
        this.city = city;
        this.num_spaces = num_spaces;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName(){
        return name;
    }

    public String getCity(){
        return city;
    }

    public int getNum_spaces() {
        return num_spaces;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNumSpaces(int num_spaces){
        this.num_spaces = num_spaces;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setCity(String city){
        this.city = city;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

}
