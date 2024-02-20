package com.hassan.dev.microservices.controller;

import com.hassan.dev.microservices.entity.Post;
import com.hassan.dev.microservices.entity.User;
import com.hassan.dev.microservices.exception.UserNotFoundException;
import com.hassan.dev.microservices.repository.PostRepo;
import com.hassan.dev.microservices.repository.UserRepo;
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
@RequestMapping("users/jpa")
public class UserJpaController {

    private final UserRepo userRepo;
    private final PostRepo postRepo;

    public UserJpaController(UserRepo userRepo, PostRepo postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        Optional<User> userOptional = this.userRepo.findById(id);
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



    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepo.findAll();
    }
    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User createdUser = this.userRepo.save(user);
        //User createdUser = this.userDaoService.addNewUser(user);

        //return the location of the new created object as a header in response
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        this.userRepo.deleteById(id);
    }


    //posts
    @GetMapping("/{id}/posts")
    public List<Post> retrievePostsOfUser(@PathVariable int id){
        Optional<User> userOptional = this.userRepo.findById(id);
        if(userOptional.isEmpty())
            throw new UserNotFoundException("User with Id: "+id+" does not exist");
        User userObject = userOptional.get();
        List<Post> posts = userObject.getPosts();

        System.err.println(posts);
        return posts;

    }

    @GetMapping("/{UserId}/posts/{postId}")
    public Post retrieveSinglePostOfUser(@PathVariable int UserId, @PathVariable int postId){
        Optional<User> userOptional = this.userRepo.findById(UserId);
        if(userOptional.isEmpty())
            throw new UserNotFoundException("User with Id: "+UserId+" does not exist");

        List<Post> posts = userOptional.get().getPosts();
        Optional<Post> postOptional = posts.stream().filter(p -> p.getId() == postId).findFirst();
        if(postOptional.isEmpty())
            throw new UserNotFoundException("Post with Id: "+postId+" does not exist");
        Post post = postOptional.get();


        return post;

    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> addPost(@Valid @RequestBody Post post, @PathVariable int id){
        Optional<User> userOptional = this.userRepo.findById(id);
        if(userOptional.isEmpty())
            throw new UserNotFoundException("User with Id: "+id+" does not exist");
        User user = userOptional.get();
        post.setUser(user);
        Post createdPost = this.postRepo.save(post);
        //User createdUser = this.userDaoService.addNewUser(user);

        //return the location of the new created object as a header in response
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


}
