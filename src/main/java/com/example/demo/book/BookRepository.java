package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

   //  Book findBookByName(String name);
    List<Book> findBookByUsersId(Long id);

}
