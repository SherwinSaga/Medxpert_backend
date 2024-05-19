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

    //reviews.jsx
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    //get the reviews of that specific medicine
    //medicine_card.jsx
    @GetMapping("/specificMed/{id}")
    public List<Review> getMedicineReview(@PathVariable Integer id){
        //find the medicine object
        Medicine medicine = medicineRepository.findById(id).orElse(null);
        if (medicine != null) {
            //return the reviews that have the same medicine id
            return reviewRepository.findBymedicineId(medicine);
        } else {
            return null;
        }
    }

    //admin side count statistics
    //adminDashboard.jsx
    @GetMapping("/countreview")
    public long countReviews() {
        return reviewRepository.count();
    }

    //admin side review statistics
    //adminDashboard.jsx
    @GetMapping("/negativeMed")
    public List<Medicine> getMedicineWithMostZeroRatings() {
        return reviewRepository.findTopMedicineWithZeroRatings();
    }

    //admin side review statistics
    //adminDashboard.jsx
    @GetMapping("/positiveMed")
    public List<Medicine> getMedicineWithMostOneRatings() {
        return reviewRepository.findTopMedicineWithOneRatings();
    }

    //add a feedback from a user to that specific medicine
    //medicine_card.jsx
    @PostMapping("/add_review")
    public Review addReview(@RequestBody Map<String, Object> reviewData, @RequestParam Integer User_id, @RequestParam Integer medicineId) throws Exception {

        //find the user object using the id
        Users user = usersRepository.findById(User_id).orElseThrow(() -> new Exception("User Not Found "));

        //find the medicine object using the id
        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(() -> new Exception("Medicine Not Found "));

        //convert the string date into a proper format accepted by java and mysql
        String reviewdateStr = (String) reviewData.get("reviewdate");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = formatter.parse(reviewdateStr);
        java.sql.Date reviewdate = new java.sql.Date(utilDate.getTime());

        //create the review entity and set the fields
        Review review = new Review();
        review.setUser_id(user);
        review.setMedicine_id(medicine);
        review.setFeedback((String) reviewData.get("feedback"));
        review.setRating((Integer) reviewData.get("rating"));
        review.setReviewdate(reviewdate);

        //then save into the database
        return reviewRepository.save(review);
    }




}
