package com.example.demo.book;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Long book_id){
        super("Could not found the book with id"+ book_id);
    }

}
