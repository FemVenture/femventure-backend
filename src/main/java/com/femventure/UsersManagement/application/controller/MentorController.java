package com.femventure.UsersManagement.application.controller;

import com.femventure.UsersManagement.domain.dto.Mentor.request.MentorRequestDto;
import com.femventure.UsersManagement.domain.dto.Mentor.response.MentorResponseDto;
import com.femventure.UsersManagement.domain.interfaces.service.IMentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name="Mentor Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class MentorController {
    private final IMentorService mentorService;

    public MentorController(IMentorService mentorService) {
        this.mentorService = mentorService;
    }

    @Operation(summary = "Create a mentor")
    @PostMapping("/mentors")
    public ResponseEntity<MentorResponseDto> createMentor(@RequestBody MentorRequestDto mentorRequestDto, @RequestParam String email, @RequestParam String password) {
        var response = mentorService.createMentor(mentorRequestDto);
        var mentor = mentorService.createUserByMentorId(response.getId(), email, password);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get mentor by id")
    @GetMapping("/mentors/{id}")
    public ResponseEntity<MentorResponseDto> getMentorById(@PathVariable Long id) {
        var response = mentorService.getMentorById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all mentors")
    @GetMapping("/mentors")
    public ResponseEntity<List<MentorResponseDto>> getAllMentors() {
        var response = mentorService.getAllMentors();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}