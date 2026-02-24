package com.example.chat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.chat.Model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

}
    

