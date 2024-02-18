package com.hassan.dev.microservices.dao;

import com.hassan.dev.microservices.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int USERSCOUNT = 0;
    static {
        users.add(new User(++USERSCOUNT, "Hassan", LocalDate.now().minusYears(27)));
        users.add(new User(++USERSCOUNT, "Shahd", LocalDate.now().minusYears(21)));
        users.add(new User(++USERSCOUNT, "Amer", LocalDate.now()));
    }

    //get all users

    public List<User> getAllUsers() {
        return users;
    }


    //add new user
    public User addNewUser(User user){
        user.setId(++USERSCOUNT);
        users.add(user);
        return user;
    }
    //get specific user
    public Optional<User> getUser(int id){
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        return user;
    }

    //delete user
    public void deleteUserById(int id){
        users.removeIf(u -> u.getId() == id);
    }



}
