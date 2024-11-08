package com.femventure.UsersManagement.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name="Entrepreneur Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class EntrepreneurController {
}
