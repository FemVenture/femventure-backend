package com.femventure.UsersManagement.application.controller;

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
    @GetMapping
    public String returnString(){
        return "Hola Usuario de Fem Venture";
    }

}
