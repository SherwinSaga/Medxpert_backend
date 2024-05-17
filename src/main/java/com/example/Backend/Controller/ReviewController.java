package com.example.Backend.Controller;

import com.example.Backend.DTO.ReviewDTO;
import com.example.Backend.Entity.Medicine;
import com.example.Backend.Entity.Review;
import com.example.Backend.Entity.Users;
import com.example.Backend.Repositories.MedicineRepository;
import com.example.Backend.Repositories.ReviewRepository;
import com.example.Backend.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }


    @GetMapping("/specificMed/{id}")
    public List<Review> getMedicineReview(@PathVariable Integer id){
        Medicine medicine = medicineRepository.findById(id).orElse(null);
        if (medicine != null) {
            return reviewRepository.findBymedicineId(medicine);
        } else {
            return null;
        }
    }

    @GetMapping("/countreview")
    public long countReviews() {
        return reviewRepository.count();
    }

    @GetMapping("/negativeMed")
    public List<Medicine> getMedicineWithMostZeroRatings() {
        return reviewRepository.findTopMedicineWithZeroRatings();
    }

    @GetMapping("/positiveMed")
    public List<Medicine> getMedicineWithMostOneRatings() {
        return reviewRepository.findTopMedicineWithOneRatings();
    }

    @PostMapping("/add_review")
    public Review addReview(@RequestBody Map<String, Object> reviewData, @RequestParam Integer User_id, @RequestParam Integer medicineId) throws Exception {
        Users user = usersRepository.findById(User_id).orElseThrow(() -> new Exception("User Not Found "));
        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(() -> new Exception("Medicine Not Found "));

        String reviewdateStr = (String) reviewData.get("reviewdate");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = formatter.parse(reviewdateStr);
        java.sql.Date reviewdate = new java.sql.Date(utilDate.getTime());

        Review review = new Review();
        review.setUser_id(user);
        review.setMedicine_id(medicine);
        review.setFeedback((String) reviewData.get("feedback"));
        review.setRating((Integer) reviewData.get("rating"));
        review.setReviewdate(reviewdate);

        return reviewRepository.save(review);
    }




}
