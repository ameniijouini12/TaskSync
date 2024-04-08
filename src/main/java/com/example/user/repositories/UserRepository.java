package com.example.user.repositories;

import com.example.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByStatus(User.Status status);
}
