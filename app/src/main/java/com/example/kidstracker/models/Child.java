package com.example.kidstracker.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Child implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private int gender;

    private int diagnosis;

    private String baseWCC;

    private String baseNextWCC;

    private String baseHearing;

    private int baseHearingResult;

    private String baseNextHearing;

    private String baseVision;

    private String baseHGB;

    private String baseTSH;

    private int priorSurgeries;

    private int priorEyeProblems;

    private int priorHearingProblems;

    private int priorNeckProblems;

    private int priorThyroidProblems;

    private int priorHeartProblems;

    private int priorBreathingProblems;

    private int priorConstipationProblems;

    private int priorSleepingProblems;

    private int priorBrainProblems;

    private int priorBloodProblems;

    private int priorImmuneProblems;

    @Ignore
    public Child(){}

    public Child(String firstName, String lastName, String dateOfBirth, int gender,
                 int diagnosis, String baseWCC, String baseNextWCC, String baseHearing,
                 int baseHearingResult, String baseNextHearing, String baseVision,
                 String baseHGB, String baseTSH, int priorSurgeries, int priorEyeProblems,
                 int priorHearingProblems, int priorNeckProblems, int priorThyroidProblems,
                 int priorHeartProblems, int priorBreathingProblems, int priorConstipationProblems,
                 int priorSleepingProblems, int priorBrainProblems, int priorBloodProblems,
                 int priorImmuneProblems) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.diagnosis = diagnosis;
        this.baseWCC = baseWCC;
        this.baseNextWCC = baseNextWCC;
        this.baseHearing = baseHearing;
        this.baseHearingResult = baseHearingResult;
        this.baseNextHearing = baseNextHearing;
        this.baseVision = baseVision;
        this.baseHGB = baseHGB;
        this.baseTSH = baseTSH;
        this.priorSurgeries = priorSurgeries;
        this.priorEyeProblems = priorEyeProblems;
        this.priorHearingProblems = priorHearingProblems;
        this.priorNeckProblems = priorNeckProblems;
        this.priorThyroidProblems = priorThyroidProblems;
        this.priorHeartProblems = priorHeartProblems;
        this.priorBreathingProblems = priorBreathingProblems;
        this.priorConstipationProblems = priorConstipationProblems;
        this.priorSleepingProblems = priorSleepingProblems;
        this.priorBrainProblems = priorBrainProblems;
        this.priorBloodProblems = priorBloodProblems;
        this.priorImmuneProblems = priorImmuneProblems;
    }

    @Ignore
    public Child(String firstName, String lastName, String dateOfBirth, int gender, int diagnosis) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(int diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getBaseWCC() {
        return baseWCC;
    }

    public void setBaseWCC(String baseWCC) {
        this.baseWCC = baseWCC;
    }

    public String getBaseNextWCC() {
        return baseNextWCC;
    }

    public void setBaseNextWCC(String baseNextWCC) {
        this.baseNextWCC = baseNextWCC;
    }

    public String getBaseHearing() {
        return baseHearing;
    }

    public void setBaseHearing(String baseHearing) {
        this.baseHearing = baseHearing;
    }

    public int getBaseHearingResult() {
        return baseHearingResult;
    }

    public void setBaseHearingResult(int baseHearingResult) {
        this.baseHearingResult = baseHearingResult;
    }

    public String getBaseNextHearing() {
        return baseNextHearing;
    }

    public void setBaseNextHearing(String baseNextHearing) {
        this.baseNextHearing = baseNextHearing;
    }

    public String getBaseVision() {
        return baseVision;
    }

    public void setBaseVision(String baseVision) {
        this.baseVision = baseVision;
    }

    public String getBaseHGB() {
        return baseHGB;
    }

    public void setBaseHGB(String baseHGB) {
        this.baseHGB = baseHGB;
    }

    public String getBaseTSH() {
        return baseTSH;
    }

    public void setBaseTSH(String baseTSH) {
        this.baseTSH = baseTSH;
    }

    public int getPriorSurgeries() {
        return priorSurgeries;
    }

    public void setPriorSurgeries(int priorSurgeries) {
        this.priorSurgeries = priorSurgeries;
    }

    public int getPriorEyeProblems() {
        return priorEyeProblems;
    }

    public void setPriorEyeProblems(int priorEyeProblems) {
        this.priorEyeProblems = priorEyeProblems;
    }

    public int getPriorHearingProblems() {
        return priorHearingProblems;
    }

    public void setPriorHearingProblems(int priorHearingProblems) {
        this.priorHearingProblems = priorHearingProblems;
    }

    public int getPriorNeckProblems() {
        return priorNeckProblems;
    }

    public void setPriorNeckProblems(int priorNeckProblems) {
        this.priorNeckProblems = priorNeckProblems;
    }

    public int getPriorThyroidProblems() {
        return priorThyroidProblems;
    }

    public void setPriorThyroidProblems(int priorThyroidProblems) {
        this.priorThyroidProblems = priorThyroidProblems;
    }

    public int getPriorHeartProblems() {
        return priorHeartProblems;
    }

    public void setPriorHeartProblems(int priorHeartProblems) {
        this.priorHeartProblems = priorHeartProblems;
    }

    public int getPriorBreathingProblems() {
        return priorBreathingProblems;
    }

    public void setPriorBreathingProblems(int priorBreathingProblems) {
        this.priorBreathingProblems = priorBreathingProblems;
    }

    public int getPriorConstipationProblems() {
        return priorConstipationProblems;
    }

    public void setPriorConstipationProblems(int priorConstipationProblems) {
        this.priorConstipationProblems = priorConstipationProblems;
    }

    public int getPriorSleepingProblems() {
        return priorSleepingProblems;
    }

    public void setPriorSleepingProblems(int priorSleepingProblems) {
        this.priorSleepingProblems = priorSleepingProblems;
    }

    public int getPriorBrainProblems() {
        return priorBrainProblems;
    }

    public void setPriorBrainProblems(int priorBrainProblems) {
        this.priorBrainProblems = priorBrainProblems;
    }

    public int getPriorBloodProblems() {
        return priorBloodProblems;
    }

    public void setPriorBloodProblems(int priorBloodProblems) {
        this.priorBloodProblems = priorBloodProblems;
    }

    public int getPriorImmuneProblems() {
        return priorImmuneProblems;
    }

    public void setPriorImmuneProblems(int priorImmuneProblems) {
        this.priorImmuneProblems = priorImmuneProblems;
    }
}
