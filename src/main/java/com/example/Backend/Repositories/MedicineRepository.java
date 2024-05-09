package com.example.Backend.Repositories;

import com.example.Backend.Entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    List<Medicine> findBymedicineNameContaining(String query);

}
