package com.example.Backend.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer User_ID;

    @JsonProperty("usersname")
    private String usersname;

    @JsonProperty("password")
    private String User_Password;

    public Integer getUser_ID() {
        return User_ID;
    }

    public String getUsersname() {
        return usersname;
    }


    public String getUser_Password() {
        return User_Password;
    }

    public void setUser_Password(String user_Password) {
        User_Password = user_Password;
    }
}
