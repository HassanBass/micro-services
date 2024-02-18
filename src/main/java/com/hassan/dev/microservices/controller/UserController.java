package com.hassan.dev.microservices.controller;

import com.hassan.dev.microservices.dao.UserDaoService;
import com.hassan.dev.microservices.entity.User;
import com.hassan.dev.microservices.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }


    @GetMapping
    public List<User> getAllUsers(){
        return this.userDaoService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable int id){
        Optional<User> user = this.userDaoService.getUser(id);
        if(user.isEmpty())
            throw new UserNotFoundException("User with Id: "+id+" does not exist");
        return this.userDaoService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User createdUser = this.userDaoService.addNewUser(user);

        //return the location of the new created object as a header in response
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
       this.userDaoService.deleteUserById(id);
    }

}
