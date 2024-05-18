package com.example.Backend.Repositories;

import com.example.Backend.Entity.User_Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface User_RolesRepository extends JpaRepository<User_Roles, Integer> {
    @Query("SELECT ur FROM User_Roles ur WHERE ur.User_ID.id = :userId")
    User_Roles findByUserId(@Param("userId") int userId);
}
