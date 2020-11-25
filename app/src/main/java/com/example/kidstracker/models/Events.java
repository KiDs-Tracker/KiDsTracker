package com.example.kidstracker.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Events {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String event;
    private String time;
    private String date;
    private String month;
    private String year;


    public Events(String event, String time, String date, String month, String year) {
        this.event = event;
        this.time = time;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
