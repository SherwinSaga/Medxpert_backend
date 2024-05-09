package com.example.Backend.Entity;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Review_ID;

    @OneToOne
    @JoinColumn( name = "User_ID")
    private Users User_id;

    @ManyToOne
    @JoinColumn( name = "medicine_id")
    private Medicine medicine_id;

    private String feedback;

    public Medicine getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Medicine medicine_id) {
        this.medicine_id = medicine_id;
    }

    public Users getUser_id() {
        return User_id;
    }

    public void setUser_id(Users user_id) {
        User_id = user_id;
    }

    public Integer getReview_ID() {
        return Review_ID;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}