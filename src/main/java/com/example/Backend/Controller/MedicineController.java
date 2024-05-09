package com.example.Backend.Controller;

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

    @GetMapping("/all_medicines")
    public List<Medicine> getAllMedicine(){
        return medicineRepository.findAll();
    }

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


    //to do
    //return specific medicine
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

    @GetMapping("/search/{medicine_id}")
    public Medicine getMedicine(@PathVariable Integer medicine_id) throws Exception{
        return medicineRepository.findById(medicine_id).orElseThrow(() -> new Exception("Medicine Not Found"));
    }

}
