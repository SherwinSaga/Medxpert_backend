package com.example.Backend.Repositories;

import com.example.Backend.Entity.Medicine;
import com.example.Backend.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findBymedicineId(Medicine id);
}
