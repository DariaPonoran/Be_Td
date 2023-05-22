package com.example.demo.user;

import com.example.demo.book.Book;
import com.example.demo.book.BookNotFoundException;
import com.example.demo.book.BookRepository;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity

@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable (
            name="bookShelf",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="book_id")
    )

     private Set<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
       return password;
    }
    public void setPassword(String password){
        this.password= password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addBook(Book book){
        this.books.add(book);
        /*if(book.getUsers()==null){
            book.getUsers() = new HashSet<User>();
        }*/
        book.getUsers().add(this);
    }

    public void removeBook(Long book_id){
       Book book= this.books.stream().filter(b-> Objects.equals(b.getBook_id(), book_id)).findAny()
                .orElseThrow(()->new BookNotFoundException(book_id));
        System.out.println(book);
        if(book!=null){
            System.out.println("merge2");
            this.books.remove(book);
            book.getUsers().remove(this);
            System.out.println("merge");
        }
    }


}
