package com.example.MarksharksAssignment_SameerSawale.Service.Impl;

import com.example.MarksharksAssignment_SameerSawale.Entity.User;
import com.example.MarksharksAssignment_SameerSawale.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username).orElseThrow(()->
                new RuntimeException("User not found"));
        return user;
    }
}
