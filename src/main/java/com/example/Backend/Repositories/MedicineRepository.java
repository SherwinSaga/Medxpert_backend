package com.example.Backend.Repositories;

import com.example.Backend.Entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    List<Medicine> findBymedicineNameContaining(String query);

    @Query(value = "SELECT * FROM Medicine ORDER BY medicine_Id DESC LIMIT 4", nativeQuery = true)
    List<Medicine> findLastFourMedicines();


}
