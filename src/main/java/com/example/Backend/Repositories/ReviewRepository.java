package com.example.Backend.Repositories;

import com.example.Backend.Entity.Medicine;
import com.example.Backend.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findBymedicineId(Medicine id);

    @Query("SELECT r.medicineId FROM Review r WHERE r.rating = 0 GROUP BY r.medicineId ORDER BY COUNT(r) DESC")
    List<Medicine> findTopMedicineWithZeroRatings();

    @Query("SELECT r.medicineId FROM Review r WHERE r.rating = 1 GROUP BY r.medicineId ORDER BY COUNT(r) DESC")
    List<Medicine> findTopMedicineWithOneRatings();
}
