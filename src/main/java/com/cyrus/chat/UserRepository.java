package com.cyrus.chat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(int phone);
    Optional<User> findByUsername(String username);
}
