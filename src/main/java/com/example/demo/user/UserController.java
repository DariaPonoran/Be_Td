package com.example.demo.user;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    @PostMapping("/signin")
    User createUser(@RequestBody User newUser){
        if(!newUser.getPassword().equals(newUser.getPasswordConfirmation())){
            throw new WrongPasswordConfirmationException();
        }
        else {
            User user=new User();
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setPasswordConfirmation(newUser.getPasswordConfirmation());
            return userRepository.save(user);
        }
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping ("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @GetMapping ("/login/emailAndPassword")
    ResponseEntity<List<User>> getUserByEmailPassword(@RequestParam String email, @RequestParam String password){
        
        return new ResponseEntity<List<User>> (userRepository.findByEmailAndPassword(email, password), HttpStatus.OK);
    }


    @PutMapping("/user/{id}")
    User updateUser(@RequestBody UserUpdateDTO body, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(body.getUsername());
                    user.setName(body.getName());
                    return userRepository.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " +id+ " has been deleted.";
    }

}
