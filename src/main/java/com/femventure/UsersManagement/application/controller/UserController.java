package com.femventure.UsersManagement.application.controller;

import com.femventure.UsersManagement.domain.dto.User.response.UserResponseDto;
import com.femventure.UsersManagement.domain.interfaces.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="User Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get user for login")
    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> getUserForLogin(@RequestParam String email, @RequestParam String password) {
        var response = userService.getUserForLogin(email, password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
