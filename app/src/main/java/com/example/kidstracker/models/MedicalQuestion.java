package com.example.kidstracker.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class MedicalQuestion implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    public MedicalQuestion(String question, String option1, String option2, String option3) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
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

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }
}
