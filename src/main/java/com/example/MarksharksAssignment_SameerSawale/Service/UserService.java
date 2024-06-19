package com.example.MarksharksAssignment_SameerSawale.Service;

import com.example.MarksharksAssignment_SameerSawale.DTOs.UserReqDTO;
import com.example.MarksharksAssignment_SameerSawale.DTOs.UserResDTO;

public interface UserService {

    public String registerUser(UserReqDTO userReqDTO) throws Exception;

    public UserResDTO fetchUser(String username) throws Exception;
}
