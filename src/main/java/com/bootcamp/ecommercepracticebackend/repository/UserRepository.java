package com.bootcamp.ecommercepracticebackend.repository;

import com.bootcamp.ecommercepracticebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}