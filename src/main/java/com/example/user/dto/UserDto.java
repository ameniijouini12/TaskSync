package com.example.user.dto;

import com.example.user.entities.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.example.user.entities.User.Status;

@Data
@Getter
@Setter
public class UserDto {

        private Long id;
        private String username;
        private String email;
        private Status status;
        @Enumerated(EnumType.STRING)
        private ERole role;


    }
