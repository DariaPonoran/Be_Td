package com.example.demo.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could not found the user with id"+ id);
    }
    public UserNotFoundException(String email, String password){
        super("Could not found the user");
    }
}
