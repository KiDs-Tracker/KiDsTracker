package com.example.kidstracker.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class RoutineScreeningQuestion implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String question;

    public RoutineScreeningQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
