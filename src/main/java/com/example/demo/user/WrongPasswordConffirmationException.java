package com.example.demo.user;

public class WrongPasswordConffirmationException  extends RuntimeException{
    public WrongPasswordConffirmationException(){
        super("Wrong password conffirmation");
    }
}
