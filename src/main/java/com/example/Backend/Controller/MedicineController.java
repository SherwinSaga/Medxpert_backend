package com.example.Backend.Controller;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.Backend.DTO.MedicineDTO;
import com.example.Backend.Entity.Medicine;
import com.example.Backend.Repositories.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;

    //get all medicines
    //wala na gamit
    @GetMapping("/all_medicines")
    public List<Medicine> getAllMedicine(){
        return medicineRepository.findAll();
    }

    //homepage medicine
    //homepage.jsx
    @GetMapping("/HP_medicine")
    public List<MedicineDTO> getHomepageMedicine(){
        return medicineRepository.findAll().stream()
                .map(medicine -> {
                    MedicineDTO dto = new MedicineDTO();
                    dto.setId(medicine.getMedicine_Id());
                    dto.setMedicine_name(medicine.getMedicine_Name());
                    dto.setMedicine_desc(medicine.getMedicine_description());
                    dto.setMedicine_uses(medicine.getMedicine_uses());
                    dto.setMedicine_side_effects(medicine.getMedicine_side_effects());
                    dto.setMedicine_dosage(medicine.getMedicine_dosage());
                    dto.setMedicine_imgUrl(medicine.getMedicine_imgUrl());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    //search medicine
    //homepage.jsx
    @GetMapping("/search")
    public List<MedicineDTO> searchMedicine(@RequestParam String query) {
        return medicineRepository.findBymedicineNameContaining(query).stream()
                .map(medicine -> {
                    MedicineDTO dto = new MedicineDTO();
                    dto.setId(medicine.getMedicine_Id());
                    dto.setMedicine_name(medicine.getMedicine_Name());
                    dto.setMedicine_desc(medicine.getMedicine_description());
                    dto.setMedicine_uses(medicine.getMedicine_uses());
                    dto.setMedicine_side_effects(medicine.getMedicine_side_effects());
                    dto.setMedicine_dosage(medicine.getMedicine_dosage());
                    dto.setMedicine_imgUrl(medicine.getMedicine_imgUrl());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    //wala na gamit maybe
    @GetMapping("/search/{medicine_id}")
    public Medicine getMedicine(@PathVariable Integer medicine_id) throws Exception{
        return medicineRepository.findById(medicine_id).orElseThrow(() -> new Exception("Medicine Not Found"));
    }

    //admin side display 4 medicine
    //adminDashboard.jsx
    @GetMapping("/last_four_medicines")
    public List<Medicine> getLastFourMedicines() {
        return medicineRepository.findLastFourMedicines();
    }

    //admin side add medicine
    //adminDashboard.jsx
    @PostMapping("/add_medicine")
    public Medicine addMedicine(@RequestBody Medicine newMedicine) {
        return medicineRepository.save(newMedicine);
    }

    //admin side medicine stats
    //adminDashboard.jsx
    @GetMapping("/countMeds")
    public long countMedicine(){
        return medicineRepository.count();
    }
}
