package com.example.demo.book_shelf;

import com.example.demo.book.BookRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/book")
public class UserBookController {
    UserRepository userRepository;
    BookRepository bookRepository;

    public UserBookController(UserRepository userRepository, BookRepository bookRepository){
        this.userRepository=userRepository;
        this.bookRepository=bookRepository;
    }
    @PostMapping
    public User saveUserWithBook(@RequestBody User user){
        return userRepository.save(user);
    }
}
