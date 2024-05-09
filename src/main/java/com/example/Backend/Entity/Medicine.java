package com.example.Backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicine_Id;

    private String medicineName;
    private String medicine_description;
    private String medicine_uses;
    private String medicine_side_effects;
    private String medicine_dosage;
    private String medicine_imgUrl;

    //debate if buhaton or di
    //private Integer medicine_frequentSearch;

    public String getMedicine_imgUrl() {
        return medicine_imgUrl;
    }

    public void setMedicine_imgUrl(String medicine_imgUrl) {
        this.medicine_imgUrl = medicine_imgUrl;
    }

    public String getMedicine_dosage() {
        return medicine_dosage;
    }

    public void setMedicine_dosage(String medicine_dosage) {
        this.medicine_dosage = medicine_dosage;
    }

    public String getMedicine_side_effects() {
        return medicine_side_effects;
    }

    public void setMedicine_side_effects(String medicine_side_effects) {
        this.medicine_side_effects = medicine_side_effects;
    }

    public String getMedicine_uses() {
        return medicine_uses;
    }

    public void setMedicine_uses(String medicine_uses) {
        this.medicine_uses = medicine_uses;
    }

    public String getMedicine_description() {
        return medicine_description;
    }

    public void setMedicine_description(String medicine_description) {
        this.medicine_description = medicine_description;
    }

    public String getMedicine_Name() {
        return medicineName;
    }

    public void setMedicine_Name(String medicine_Name) {
        this.medicineName = medicine_Name;
    }

    public Integer getMedicine_Id() {
        return medicine_Id;
    }




}
