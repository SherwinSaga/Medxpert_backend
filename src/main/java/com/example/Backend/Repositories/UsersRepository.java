package com.example.Backend.Repositories;

import com.example.Backend.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByusersname(String user_Name);

}

