package com.femventure.ProjectManagement.application.controller;


import com.femventure.ProjectManagement.domain.dto.Project.request.FavoriteProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.FavoriteProjectResponseDto;
import com.femventure.ProjectManagement.domain.entity.FavoriteProject;
import com.femventure.ProjectManagement.domain.interfaces.service.IFavoriteProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Favorite Project Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/favorite-projects")
public class FavoriteProjectController {

    private final IFavoriteProjectService favoriteProjectService;

    public FavoriteProjectController(IFavoriteProjectService favoriteProjectService) {
        this.favoriteProjectService = favoriteProjectService;
    }

    @Operation(summary = "Mark a project as favorite")
    @PostMapping("/project/{projectId}/entrepreneur/{entrepreneurId}")
    public ResponseEntity<FavoriteProjectResponseDto> markProjectAsFavorite(@PathVariable Long projectId, @PathVariable Long entrepreneurId) {
        FavoriteProjectResponseDto response = favoriteProjectService.markProjectAsFavorite(projectId, entrepreneurId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Unmark a project as favorite")
    @DeleteMapping("/project/{projectId}/entrepreneur/{entrepreneurId}")
    public ResponseEntity<Void> unmarkProjectAsFavorite(@PathVariable Long projectId, @PathVariable Long entrepreneurId) {
        favoriteProjectService.unmarkProjectAsFavorite(projectId, entrepreneurId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all favorite projects by entrepreneur ID")
    @GetMapping("/entrepreneur/{entrepreneurId}")
    public ResponseEntity<List<FavoriteProjectResponseDto>> getFavoriteProjectsByEntrepreneur(@PathVariable Long entrepreneurId) {
        List<FavoriteProjectResponseDto> response = favoriteProjectService.getFavoriteProjectsByEntrepreneur(entrepreneurId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
