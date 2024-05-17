package com.example.Backend.Controller;

import com.example.Backend.DTO.ReviewDTO;
import com.example.Backend.Entity.Medicine;
import com.example.Backend.Entity.Review;
import com.example.Backend.Repositories.MedicineRepository;
import com.example.Backend.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    //to do
    //get review of that specific user
    @GetMapping("/specificMed/{id}")
    public List<Review> getMedicineReview(@PathVariable Integer id){
        Medicine medicine = medicineRepository.findById(id).orElse(null);
        if (medicine != null) {
            return reviewRepository.findBymedicineId(medicine);
        } else {
            return null; // or handle this case as you see fit
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
}
