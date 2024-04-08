package com.example.user.services;




import com.example.user.entities.User;
import com.example.user.dto.UserDto;
import com.example.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findByStatus(User.Status status) {
        return userRepository.findByStatus(status).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserDto convertToDTO(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        return dto;
    }
}

