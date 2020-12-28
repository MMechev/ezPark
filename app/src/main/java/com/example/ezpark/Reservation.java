package com.example.ezpark;

import android.graphics.Bitmap;

public class Reservation {

    private int id;
    private String username;
    private String city;
    private String parking;
    private String time;
    private String date;
    private Bitmap bitmap;

    public Reservation(){

    }

    public Reservation(String username, String city, String parking, String time, String date, Bitmap bitmap){
        this.username = username;
        this.city = city;
        this.parking = parking;
        this.time = time;
        this.date = date;
        this.bitmap = bitmap;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setParking(String parking){
        this.parking = parking;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getUsername(){
        return this.username;
    }

    public String getCity(){
        return this.city;
    }

    public String getParking(){
        return this.parking;
    }

    public String getTime(){
        return this.time;
    }

    public String getDate(){
        return this.date;
    }

    public void setId(int id){
        this.id = id;
    }

    public Bitmap getBitmap(){
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public int getId(){
        return this.id;
    }


}

