package com.example.Backend.Controller;

import com.example.Backend.Entity.Roles;
import com.example.Backend.Entity.User_Roles;
import com.example.Backend.Repositories.User_RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_roles")
public class User_RolesController {

    @Autowired
    private User_RolesRepository userRolesRepository;

    //get the role of a user
    //wala na gamit
    @GetMapping("/{user_id}")
    public Roles getRoleOfUser(@PathVariable Integer user_id) throws Exception {
        User_Roles userRole = userRolesRepository.findById(user_id).orElseThrow(() -> new Exception("User_Roles error : line 21 @User_RolesController"));

        return userRole.getRole_ID();
    }


}
