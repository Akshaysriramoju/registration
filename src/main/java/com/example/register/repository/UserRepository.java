package com.example.register.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.register.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);
}
