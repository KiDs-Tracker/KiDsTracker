package com.example.kidstracker.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Provider implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int gender;
    private String location;
    private int physician;
    private String number;
    private String patientName;

    public Provider(String name, int gender, String location, int physician, String number, String patientName) {
        this.name = name;
        this.gender = gender;
        this.location = location;
        this.physician = physician;
        this.number = number;
        this.patientName = patientName;


    }
    @Ignore
    public Provider(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhysician() {
        return physician;
    }

    public void setPhysician(int physician) {
        this.physician = physician;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
