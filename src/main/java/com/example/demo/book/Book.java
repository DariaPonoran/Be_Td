package com.example.demo.book;

import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Book {
    @Id
    @GeneratedValue()
    private Long book_id;
    @Column(unique = true)
    @NotBlank
    private String Name;
    @Column(unique = true)
    @NotBlank
    private String Author;


    @JsonIgnore
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY, cascade={
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
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

    public Set<User> getUsers(){
        return users;
    }
    public void setUsers(Set<User> users){
        this.users=users;
    }

}
