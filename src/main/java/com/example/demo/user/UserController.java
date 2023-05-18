package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserRespository userRespository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRespository.save(newUser);
    }
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRespository.findAll();
    }

    @GetMapping ("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRespository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @GetMapping ("/login/emailAndPassword")
    ResponseEntity<List<User>> getUserByEmailPassword(@RequestParam String email, @RequestParam String password){
        
        return new ResponseEntity<List<User>> (userRespository.findByEmailAndPassword(email, password), HttpStatus.OK);

               // .orElseThrow(()-> new UserNotFoundException(email, password));
    }


    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRespository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setBookId(newUser.getBookId());
                    return userRespository.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRespository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRespository.deleteById(id);
        return "User with id " +id+ " has been deleted.";
    }
}
