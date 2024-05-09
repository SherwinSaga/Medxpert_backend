package com.example.Backend.DTO;

public class MedicineDTO {
    private Integer id;
    private String medicine_name;
    private String medicine_desc;
    private String medicine_uses;
    private String medicine_side_effects;
    private String medicine_dosage;
    private String medicine_imgUrl;

    public String getMedicine_imgUrl() {
        return medicine_imgUrl;
    }

    public void setMedicine_imgUrl(String medicine_imgUrl) {
        this.medicine_imgUrl = medicine_imgUrl;
    }

    public String getMedicine_uses() {
        return medicine_uses;
    }

    public void setMedicine_uses(String medicine_uses) {
        this.medicine_uses = medicine_uses;
    }

    public String getMedicine_side_effects() {
        return medicine_side_effects;
    }

    public void setMedicine_side_effects(String medicine_side_effects) {
        this.medicine_side_effects = medicine_side_effects;
    }

    public String getMedicine_dosage() {
        return medicine_dosage;
    }

    public void setMedicine_dosage(String medicine_dosage) {
        this.medicine_dosage = medicine_dosage;
    }



    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getMedicine_desc() {
        return medicine_desc;
    }

    public void setMedicine_desc(String medicine_desc) {
        this.medicine_desc = medicine_desc;
    }
}
