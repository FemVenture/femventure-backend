package com.femventure.UsersManagement.domain.interfaces.service;

import com.femventure.UsersManagement.domain.dto.Entrepreneur.request.EntrepreneurRequestDto;
import com.femventure.UsersManagement.domain.dto.Entrepreneur.response.EntrepreneurResponseDto;

import java.util.List;

public interface IEntrepreneurService {
    //POST
    public abstract EntrepreneurResponseDto createEntrepreneur(EntrepreneurRequestDto entrepreneur);
    public abstract EntrepreneurResponseDto createUserByEntrepreneurId(Long id, String email, String password);

    //GET
    public abstract EntrepreneurResponseDto getEntrepreneurById(Long id);
    public abstract List<EntrepreneurResponseDto> getAllEntrepreneurs();
    //PUT
    public abstract EntrepreneurResponseDto updateEntrepreneurByUserId(Long EntrepreneurId, Long userId);
}
