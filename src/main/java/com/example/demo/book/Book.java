package com.example.demo.book;

import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity

public class Book {
    @Id
    @GeneratedValue
    private Long book_id;
    private String Name;
    private String Author;


    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)

    private Set<User> users;

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
         Author = author;
    }

}
