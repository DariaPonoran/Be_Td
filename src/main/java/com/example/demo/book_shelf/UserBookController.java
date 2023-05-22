package com.example.demo.book_shelf;

import com.example.demo.book.Book;
import com.example.demo.book.BookNotFoundException;
import com.example.demo.book.BookRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserNotFoundException;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/book")
public class UserBookController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    public UserBookController(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public User saveUserWithBook(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Book> addBook(@PathVariable(value = "id") Long id, @RequestBody Book bookRequest) {
        Book book = userRepository.findById(id).map(user -> {
            Long book_id = bookRequest.getBook_id();

            //book exists
            if (book_id != 0L) {
                Book _book = bookRepository.findById(book_id).orElseThrow(null);
                user.addBook(_book);
                userRepository.save(user);
                return _book;
            }
            //add and create new Book
            else {
                System.out.println("mesaj");
                user.addBook(bookRequest);
                return bookRepository.save(bookRequest);
            }
        }).orElseThrow(() -> new UserNotFoundException(id));
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}/{book_id}")
    public String deleteBookFromUser(@PathVariable(value = "id") Long id, @PathVariable(value = "book_id") Long book_id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        System.out.println(user);
        user.removeBook(book_id);
        userRepository.save(user);
        return "Book deleted from user";
    }

}
