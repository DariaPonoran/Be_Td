package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRespository extends JpaRepository<User, Long> {
   List<User> findByEmailAndPassword(String email, String password);
}
