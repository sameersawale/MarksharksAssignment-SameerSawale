package com.example.MarksharksAssignment_SameerSawale.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRes {

    private String username;

    private String token;

}
