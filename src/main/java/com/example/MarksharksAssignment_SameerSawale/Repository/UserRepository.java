package com.example.MarksharksAssignment_SameerSawale.Repository;

import com.example.MarksharksAssignment_SameerSawale.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
