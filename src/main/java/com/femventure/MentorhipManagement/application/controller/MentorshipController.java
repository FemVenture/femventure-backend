package com.femventure.MentorhipManagement.application.controller;
import com.femventure.MentorhipManagement.domain.dto.Mentorship.request.MentorshipRequestDto;
import com.femventure.MentorhipManagement.domain.dto.Mentorship.response.MentorshipResponseDto;
import com.femventure.MentorhipManagement.domain.interfaces.service.IMentorshipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Mentorship Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class MentorshipController {
    private final IMentorshipService mentorshipService;

    public MentorshipController(IMentorshipService mentorshipService) {
        this.mentorshipService = mentorshipService;
    }

    @Operation(summary = "Create a mentorship")
    @PostMapping("/mentorships")
    public ResponseEntity<MentorshipResponseDto> createMentorship(@RequestBody MentorshipRequestDto mentorshipRequestDto) {
        var response = mentorshipService.createMentorship(mentorshipRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get mentorship by mentorId")
    @GetMapping("/mentorships/{mentorId}")
    public ResponseEntity<MentorshipResponseDto> getMentorshipByMentorId(@PathVariable Long mentorId) {
        var response = mentorshipService.getMentorshipByMentorId(mentorId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get mentorship active count")
    @GetMapping("/mentorships/count")
    public ResponseEntity<Integer> getMentorshipCount() {
        var response = mentorshipService.getMentorshipCount();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update mentorship by mentorId")
    @PatchMapping("/mentorships/{mentorId}")
    public ResponseEntity<MentorshipResponseDto> updateMentorshipByMentorId(@PathVariable Long mentorId, @RequestBody MentorshipRequestDto mentorshipRequestDto) {
        var response = mentorshipService.updateMentorshipByMentorId(mentorId, mentorshipRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}