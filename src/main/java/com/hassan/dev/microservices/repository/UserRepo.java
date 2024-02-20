package com.hassan.dev.microservices.repository;

import com.hassan.dev.microservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
