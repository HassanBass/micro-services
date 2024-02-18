package com.hassan.dev.microservices.controller;

import com.hassan.dev.microservices.dao.UserDaoService;
import com.hassan.dev.microservices.entity.User;
import com.hassan.dev.microservices.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    //localhost:8081/users
    //return entity model
    //WebMVCLinkBuilder
    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        Optional<User> userOptional = this.userDaoService.getUser(id);
        if(userOptional.isEmpty())
            throw new UserNotFoundException("User with Id: "+id+" does not exist");
        User userObject = userOptional.get();
        EntityModel entityModel = EntityModel.of(userObject);
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.
                linkTo(WebMvcLinkBuilder.
                        methodOn(this.getClass())
                        .getAllUsers());
       entityModel.add(webMvcLinkBuilder.withRel("all-users"));
        return entityModel;
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
