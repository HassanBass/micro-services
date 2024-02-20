package com.hassan.dev.microservices.repository;

import com.hassan.dev.microservices.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
