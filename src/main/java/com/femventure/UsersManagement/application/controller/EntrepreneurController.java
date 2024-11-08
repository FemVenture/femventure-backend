package com.femventure.UsersManagement.application.controller;

import com.femventure.UsersManagement.domain.dto.Entrepreneur.request.EntrepreneurRequestDto;
import com.femventure.UsersManagement.domain.dto.Entrepreneur.response.EntrepreneurResponseDto;
import com.femventure.UsersManagement.domain.interfaces.service.IEntrepreneurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name="Entrepreneur Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class EntrepreneurController {

    private final IEntrepreneurService entrepreneurService;

    public EntrepreneurController(IEntrepreneurService entrepreneurService) {
        this.entrepreneurService = entrepreneurService;
    }

    @Operation(summary = "Create a Entrepreneur")
    @PostMapping("/entrepreneurs")
    public ResponseEntity<EntrepreneurResponseDto> createEntrepreneur(@RequestBody EntrepreneurRequestDto EntrepreneurRequestDto, @RequestParam String email, @RequestParam String password) {
        var response = entrepreneurService.createEntrepreneur(EntrepreneurRequestDto);
        var Entrepreneur = entrepreneurService.createUserByEntrepreneurId(response.getId(), email, password);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Entrepreneur by id")
    @GetMapping("/entrepreneurs/{id}")
    public ResponseEntity<EntrepreneurResponseDto> getEntrepreneurById(@PathVariable Long id) {
        var response = entrepreneurService.getEntrepreneurById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all Entrepreneurs")
    @GetMapping("/entrepreneurs")
    public ResponseEntity<List<EntrepreneurResponseDto>> getAllEntrepreneurs() {
        var response = entrepreneurService.getAllEntrepreneurs();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
