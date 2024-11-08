package com.femventure.UsersManagement.service;

import com.femventure.Shared.exception.ResourceNotFoundException;
import com.femventure.UsersManagement.application.validation.EntrepreneurValidation;
import com.femventure.UsersManagement.domain.dto.Entrepreneur.request.EntrepreneurRequestDto;
import com.femventure.UsersManagement.domain.dto.Entrepreneur.response.EntrepreneurResponseDto;
import com.femventure.UsersManagement.domain.entity.Entrepreneur;
import com.femventure.UsersManagement.domain.interfaces.repository.IEntrepreneurRepository;
import com.femventure.UsersManagement.domain.interfaces.service.IEntrepreneurService;
import com.femventure.UsersManagement.shared.UserQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntrepreneurServiceImpl implements IEntrepreneurService {
    private final IEntrepreneurRepository EntrepreneurRepository;
    private final ModelMapper modelMapper;
    private final UserQueryService userQueryService;

    public EntrepreneurServiceImpl(IEntrepreneurRepository EntrepreneurRepository, ModelMapper modelMapper, UserQueryService userQueryService) {
        this.EntrepreneurRepository = EntrepreneurRepository;
        this.modelMapper = modelMapper;
        this.userQueryService = userQueryService;
    }

    @Override
    public EntrepreneurResponseDto createEntrepreneur(EntrepreneurRequestDto EntrepreneurRequestDto) {

        EntrepreneurValidation.ValidateEntrepreneur(EntrepreneurRequestDto);

        var newEntrepreneur = modelMapper.map(EntrepreneurRequestDto, Entrepreneur.class);
        var createdEntrepreneur = EntrepreneurRepository.save(newEntrepreneur);
        return modelMapper.map(createdEntrepreneur, EntrepreneurResponseDto.class);
    }

    @Override
    public EntrepreneurResponseDto getEntrepreneurById(Long id) {
        var Entrepreneur = EntrepreneurRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No existe un Entrepreneur con ese id")
        );

        return modelMapper.map(Entrepreneur, EntrepreneurResponseDto.class);
    }

    @Override
    public List<EntrepreneurResponseDto> getAllEntrepreneurs() {
        var Entrepreneurs = EntrepreneurRepository.findAll();
        return Entrepreneurs.stream()
                .map(Entrepreneur -> modelMapper.map(Entrepreneur, EntrepreneurResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EntrepreneurResponseDto createUserByEntrepreneurId(Long EntrepreneurId, String email, String password) {
        var user = userQueryService.createUser(email, password);
        return updateEntrepreneurByUserId(EntrepreneurId, user.getId());
    }

    @Override
    public EntrepreneurResponseDto updateEntrepreneurByUserId(Long EntrepreneurId, Long userId) {
        var Entrepreneur = EntrepreneurRepository.findById(EntrepreneurId).orElseThrow(() ->
                new ResourceNotFoundException("No existe un emprendedor con ese id")
        );

        Entrepreneur.setUser_id(userId);
        var updatedEntrepreneur = EntrepreneurRepository.save(Entrepreneur);
        return modelMapper.map(updatedEntrepreneur, EntrepreneurResponseDto.class);
    }
}
