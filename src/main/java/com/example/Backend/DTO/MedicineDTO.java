package com.example.Backend.DTO;

public class MedicineDTO {
    private String medicine_name;
    private String medicine_desc;


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
