package com.example.user.controllers;




import com.example.user.dto.UserDto;
import com.example.user.entities.User;
import com.example.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/status/{status}")
    public List<UserDto> getUsersByStatus(@PathVariable User.Status status) {
        return userService.findByStatus(status);
    }
}

