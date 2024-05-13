package com.example.Backend.Entity;

import jakarta.persistence.*;

import java.sql.Date;

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
    private Medicine medicineId;

    private String feedback;

    private Integer rating;

    private Date reviewdate;

    public void setReview_ID(Integer review_ID) {
        Review_ID = review_ID;
    }

    public Date getReviewdate() {
        return reviewdate;
    }

    public void setReviewdate(Date reviewdate) {
        this.reviewdate = reviewdate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Medicine getMedicine_id() {
        return medicineId;
    }

    public void setMedicine_id(Medicine medicine_id) {
        this.medicineId = medicine_id;
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