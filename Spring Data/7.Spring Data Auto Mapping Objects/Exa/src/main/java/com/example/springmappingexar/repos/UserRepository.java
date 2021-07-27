package com.example.springmappingexar.repos;

import com.example.springmappingexar.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByAndEmailAndPassword(String email, String password);

}
