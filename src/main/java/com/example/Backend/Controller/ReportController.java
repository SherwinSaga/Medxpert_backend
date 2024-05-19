package com.example.Backend.Controller;


import com.example.Backend.Entity.Report;
import com.example.Backend.Entity.Review;
import com.example.Backend.Entity.Users;
import com.example.Backend.Repositories.ReportRepository;
import com.example.Backend.Repositories.ReviewRepository;
import com.example.Backend.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UsersRepository usersRepository;

    //there are same reviews reported by different user
    //get unique reports
    //adminDashboard.jsx
    @GetMapping
    public List<Report> getAllReports() {
        List<Report> allReports = reportRepository.findAll();
        Set<Review> seenReviews = new HashSet<>();
        List<Report> uniqueReports = allReports.stream()
                .filter(report -> seenReviews.add(report.getReportedReviewID()))
                .collect(Collectors.toList());
        return uniqueReports;
    }

    //report a review
    //reviewCard.jsx
    @PostMapping("/add/{review_id}/{user_Id}")
    public Report addReport(@PathVariable Integer review_id, @PathVariable Integer user_Id) {
        Review review = reviewRepository.findById(review_id).orElseThrow();
        Users user = usersRepository.findById(user_Id).orElseThrow();

        Report report = new Report();
        report.setReported_reviewID(review);
        report.setReportedByUser(user);

        return reportRepository.save(report);
    }


    //check and delete first the reports
    //then delete the review
    //adminDashboard.jsx
    @DeleteMapping("/delete_review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Integer reviewId) {
        try {
            Optional<Review> optionalReview = reviewRepository.findById(reviewId);

            if (optionalReview.isPresent()) {
                Review review = optionalReview.get();

                //find the reports that will be deleted then
                List<Report> reportsToDelete = reportRepository.findByReportedReviewID(review);

                //delete
                reportRepository.deleteAll(reportsToDelete);

                //delete the review
                reviewRepository.delete(review);
                return ResponseEntity.ok("Review and Reports deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete review");
        }
    }

    @DeleteMapping("ignore/{reportID}")
    public ResponseEntity<String> deleteReportById(@PathVariable Integer reportID) {
        reportRepository.deleteById(reportID);
        return ResponseEntity.status(HttpStatus.OK).body("Report with ID " + reportID + " has been deleted.");
    }

}
