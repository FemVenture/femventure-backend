package com.femventure.UsersManagement.domain.interfaces.service;

import com.femventure.UsersManagement.domain.dto.Entrepeneur.request.EntrepreneurRequestDto;
import com.femventure.UsersManagement.domain.dto.Entrepeneur.response.EntrepreneurResponseDto;

public interface IEntrepreneurService {
    //POST
    public abstract EntrepreneurResponseDto createMentor(EntrepreneurRequestDto entrepreneur);
}
