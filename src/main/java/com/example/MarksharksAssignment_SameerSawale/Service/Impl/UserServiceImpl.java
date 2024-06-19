package com.example.MarksharksAssignment_SameerSawale.Service.Impl;

import com.example.MarksharksAssignment_SameerSawale.DTOs.UserReqDTO;
import com.example.MarksharksAssignment_SameerSawale.DTOs.UserResDTO;
import com.example.MarksharksAssignment_SameerSawale.Entity.User;
import com.example.MarksharksAssignment_SameerSawale.Repository.UserRepository;
import com.example.MarksharksAssignment_SameerSawale.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public String registerUser(UserReqDTO userReqDTO) throws Exception {
        User user=User.builder()
                .firstname(userReqDTO.getFirstname())
                .lastname(userReqDTO.getLastname())
                .username(userReqDTO.getUsername())
                .email(userReqDTO.getEmail())
                .password(passwordEncoder.encode(userReqDTO.getPassword()))
                .contact(userReqDTO.getContact())
                .build();

        userRepository.save(user);

        return "user with username "+ user.getUsername()+" registered successfully...";

    }

    @Override
    public UserResDTO fetchUser(String username) throws Exception {
        User user=userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("user not found"));
        UserResDTO userResDTO=UserResDTO.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .contact(user.getContact())
                .build();
        return userResDTO;
    }
}
