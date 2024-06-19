package com.example.MarksharksAssignment_SameerSawale.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResDTO {

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String contact;
}

