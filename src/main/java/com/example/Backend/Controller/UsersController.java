package com.example.Backend.Controller;

import com.example.Backend.Entity.Users;
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

    @GetMapping("/{user_id}")
    public Users getUser(@PathVariable Integer user_id) throws Exception {
        return usersRepository.findById(user_id).orElseThrow(() -> new Exception("User Not Found "));
    }

    @PostMapping("/createUser")
    public Users createUser(@RequestBody Users user) {
        try {
            return usersRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not saved", e);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Users existinguser = usersRepository.findByusersname(username);

        if (existinguser != null && existinguser.getUser_Password().equals(password)) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login Success");
            return ResponseEntity.ok(response);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }
}
