package com.example.Backend.DTO;

import com.example.Backend.Entity.Roles;

public class UserDTO {
    private Integer user_id;
    private String usersname;
    private Roles user_role;

    public Roles getUser_role() {
        return user_role;
    }

    public void setUser_role(Roles user_role) {
        this.user_role = user_role;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsersname() {
        return usersname;
    }

    public void setUsersname(String usersname) {
        this.usersname = usersname;
    }
}
