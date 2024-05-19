package com.example.Backend.Entity;

import jakarta.persistence.*;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportID;

    @ManyToOne(cascade = CascadeType.ALL)
    private Review reportedReviewID;

    @ManyToOne
    private Users reportedByUser;

    public Integer getReportID() {
        return reportID;
    }

    public void setReportID(Integer reportID) {
        this.reportID = reportID;
    }

    public Users getReportedByUser() {
        return reportedByUser;
    }

    public void setReportedByUser(Users reportedByUser) {
        this.reportedByUser = reportedByUser;
    }

    public Review getReportedReviewID() {
        return reportedReviewID;
    }

    public void setReported_reviewID(Review reported_reviewID) {
        this.reportedReviewID = reported_reviewID;
    }
}


