package com.example.Backend.Controller;

import com.example.Backend.DTO.MedicineDTO;
import com.example.Backend.Entity.Medicine;
import com.example.Backend.Repositories.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                    dto.setMedicine_name(medicine.getMedicine_Name());
                    dto.setMedicine_desc(medicine.getMedicine_description());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    //to do
    //return specific medicine
}
