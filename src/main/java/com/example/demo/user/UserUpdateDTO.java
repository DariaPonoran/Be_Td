package com.example.demo.user;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserUpdateDTO {
    private String username;
    private String name;
    public UserUpdateDTO(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
