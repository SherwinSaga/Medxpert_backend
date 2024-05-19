package com.example.Backend.Repositories;

import com.example.Backend.Entity.Report;
import com.example.Backend.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findByReportedReviewID(Review review);
}
