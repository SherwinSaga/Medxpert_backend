package com.example.Backend.Entity;

import jakarta.persistence.*;

@Entity
public class User_Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn( name = "User_ID")
    private Users User_ID;

    @ManyToOne
    @JoinColumn( name = "Role_ID")
    private Roles Role_ID;


    public Roles getRole_ID() {
        return Role_ID;
    }

    public void setRole_ID(Roles role_ID) {
        Role_ID = role_ID;
    }

    public Users getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(Users user_ID) {
        User_ID = user_ID;
    }

    public int getId() {
        return id;
    }

}