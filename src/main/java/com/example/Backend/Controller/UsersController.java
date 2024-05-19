package com.example.Backend.Controller;

import com.example.Backend.DTO.UserDTO;
import com.example.Backend.Entity.Roles;
import com.example.Backend.Entity.User_Roles;
import com.example.Backend.Entity.Users;
import com.example.Backend.Repositories.RolesRepository;
import com.example.Backend.Repositories.User_RolesRepository;
import com.example.Backend.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private User_RolesRepository userRolesRepository;

    @Autowired
    private RolesRepository rolesRepository;

    //wala na gamit
    @GetMapping("/{user_id}")
    public Users getUser(@PathVariable Integer user_id) throws Exception {
        return usersRepository.findById(user_id).orElseThrow(() -> new Exception("User Not Found "));
    }

    //wala na gamit
    @PostMapping("/createUser")
    public Users createUser(@RequestBody Users user) {
        try {
            return usersRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not saved", e);
        }
    }

    //log in function
    //login.jsx
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        //check if username exists
        Users existingUser = usersRepository.findByusersname(username);

        //check if password is correct
        if (existingUser != null && existingUser.getUser_Password().equals(password)) {

            //find the role id from the role object of that user
            User_Roles userRole = userRolesRepository.findByUserId(existingUser.getUser_ID());

            //put into response map
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login Success");

            //data to object
            UserDTO userDTO = new UserDTO();
            userDTO.setUser_id(existingUser.getUser_ID());
            userDTO.setUsersname(existingUser.getUsersname());
            userDTO.setUser_role(userRole.getRole_ID());

            response.put("user", userDTO);

            return ResponseEntity.ok(response);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }


    //register.jsx
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody Users user) {
        try {
            //register for normal user
            //find the role object using the user role id
            Roles role = rolesRepository.findById(2).orElseThrow(() -> new Exception("Role not found"));

            //create the user
            Users newUser = usersRepository.save(user);

            //create the user_roles
            User_Roles userRole = new User_Roles();
            userRole.setUser_ID(newUser);
            userRole.setRole_ID(role);

            //save the user roles
            userRolesRepository.save(userRole);

            return ResponseEntity.ok().body(newUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register user", e);
        }
    }


}
