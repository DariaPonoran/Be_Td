package com.example.demo.book;

import com.example.demo.user.User;
import com.example.demo.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;


    @PostMapping("/book")
    Book newBook(@RequestBody Book newBook){ return bookRepository.save(newBook);}

    @GetMapping("/books")
    List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/book/{book_id}")
    Book getBookById(@PathVariable Long book_id){
        return bookRepository.findById((book_id))
                .orElseThrow(()->new BookNotFoundException(book_id));
    }
    @PutMapping("/book/{book_id}")
    Book updateBook(@RequestBody Book newBook, @PathVariable Long book_id){
        return bookRepository.findById(book_id)
                .map(book-> {
                    book.setName(newBook.getName());
                    book.setAuthor(newBook.getAuthor());
                    return bookRepository.save(book);
                }).orElseThrow(()-> new UserNotFoundException(book_id));
    }


    @DeleteMapping("/book/{book_id}")
    String deleteBook(@PathVariable Long book_id){
        if(!bookRepository.existsById(book_id)){
            throw new UserNotFoundException(book_id);
        }
        bookRepository.deleteById(book_id);
        return "Book with id " +book_id+ " has been deleted.";
    }

}
