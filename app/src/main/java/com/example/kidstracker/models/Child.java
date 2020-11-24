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

    private String baseHearingResult;

    private String baseNextHearing;

    private String baseVision;

    private String baseHGB;

    private String baseTSH;

    private String priorSurgeries;

    private String priorEyeProblems;

    private String priorHearingProblems;

    private String priorNeckProblems;

    private String priorThyroidProblems;

    private String priorHeartProblems;

    private String priorBreathingProblems;

    private String priorConstipationProblems;

    private String priorSleepingProblems;

    private String priorBrainProblems;

    private String priorBloodProblems;

    private String priorImmuneProblems;

    public Child(String firstName, String lastName, String dateOfBirth, int gender,
                 int diagnosis, String baseWCC, String baseNextWCC, String baseHearing,
                 String baseHearingResult, String baseNextHearing, String baseVision,
                 String baseHGB, String baseTSH, String priorSurgeries, String priorEyeProblems,
                 String priorHearingProblems, String priorNeckProblems, String priorThyroidProblems,
                 String priorHeartProblems, String priorBreathingProblems, String priorConstipationProblems,
                 String priorSleepingProblems, String priorBrainProblems, String priorBloodProblems,
                 String priorImmuneProblems) {
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

    public String getBaseHearingResult() {
        return baseHearingResult;
    }

    public void setBaseHearingResult(String baseHearingResult) {
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

    public String getPriorSurgeries() {
        return priorSurgeries;
    }

    public void setPriorSurgeries(String priorSurgeries) {
        this.priorSurgeries = priorSurgeries;
    }

    public String getPriorEyeProblems() {
        return priorEyeProblems;
    }

    public void setPriorEyeProblems(String priorEyeProblems) {
        this.priorEyeProblems = priorEyeProblems;
    }

    public String getPriorHearingProblems() {
        return priorHearingProblems;
    }

    public void setPriorHearingProblems(String priorHearingProblems) {
        this.priorHearingProblems = priorHearingProblems;
    }

    public String getPriorNeckProblems() {
        return priorNeckProblems;
    }

    public void setPriorNeckProblems(String priorNeckProblems) {
        this.priorNeckProblems = priorNeckProblems;
    }

    public String getPriorThyroidProblems() {
        return priorThyroidProblems;
    }

    public void setPriorThyroidProblems(String priorThyroidProblems) {
        this.priorThyroidProblems = priorThyroidProblems;
    }

    public String getPriorHeartProblems() {
        return priorHeartProblems;
    }

    public void setPriorHeartProblems(String priorHeartProblems) {
        this.priorHeartProblems = priorHeartProblems;
    }

    public String getPriorBreathingProblems() {
        return priorBreathingProblems;
    }

    public void setPriorBreathingProblems(String priorBreathingProblems) {
        this.priorBreathingProblems = priorBreathingProblems;
    }

    public String getPriorConstipationProblems() {
        return priorConstipationProblems;
    }

    public void setPriorConstipationProblems(String priorConstipationProblems) {
        this.priorConstipationProblems = priorConstipationProblems;
    }

    public String getPriorSleepingProblems() {
        return priorSleepingProblems;
    }

    public void setPriorSleepingProblems(String priorSleepingProblems) {
        this.priorSleepingProblems = priorSleepingProblems;
    }

    public String getPriorBrainProblems() {
        return priorBrainProblems;
    }

    public void setPriorBrainProblems(String priorBrainProblems) {
        this.priorBrainProblems = priorBrainProblems;
    }

    public String getPriorBloodProblems() {
        return priorBloodProblems;
    }

    public void setPriorBloodProblems(String priorBloodProblems) {
        this.priorBloodProblems = priorBloodProblems;
    }

    public String getPriorImmuneProblems() {
        return priorImmuneProblems;
    }

    public void setPriorImmuneProblems(String priorImmuneProblems) {
        this.priorImmuneProblems = priorImmuneProblems;
    }
}
