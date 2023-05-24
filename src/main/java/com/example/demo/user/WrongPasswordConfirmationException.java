package com.example.demo.user;

public class WrongPasswordConfirmationException extends RuntimeException{
    public WrongPasswordConfirmationException(){
        super("Wrong password confirmation");
    }
}
