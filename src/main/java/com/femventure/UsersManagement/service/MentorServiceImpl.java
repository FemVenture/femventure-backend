package com.femventure.UsersManagement.service;

import com.femventure.Shared.exception.ResourceNotFoundException;
import com.femventure.UsersManagement.application.validation.MentorValidation;
import com.femventure.UsersManagement.domain.dto.Mentor.request.MentorRequestDto;
import com.femventure.UsersManagement.domain.dto.Mentor.response.MentorResponseDto;
import com.femventure.UsersManagement.domain.entity.Mentor;
import com.femventure.UsersManagement.domain.interfaces.repository.IMentorRepository;
import com.femventure.UsersManagement.domain.interfaces.service.IMentorService;
import com.femventure.UsersManagement.shared.UserQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentorServiceImpl implements IMentorService {
    private final IMentorRepository mentorRepository;
    private final ModelMapper modelMapper;
    private final UserQueryService userQueryService;

    public MentorServiceImpl(IMentorRepository mentorRepository, ModelMapper modelMapper, UserQueryService userQueryService) {
        this.mentorRepository = mentorRepository;
        this.modelMapper = modelMapper;
        this.userQueryService = userQueryService;
    }

    @Override
    public MentorResponseDto createMentor(MentorRequestDto mentorRequestDto) {

        MentorValidation.ValidateMentor(mentorRequestDto);

        var newMentor = modelMapper.map(mentorRequestDto, Mentor.class);
        var createdMentor = mentorRepository.save(newMentor);
        return modelMapper.map(createdMentor, MentorResponseDto.class);
    }

    @Override
    public MentorResponseDto getMentorById(Long id) {
        var mentor = mentorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No existe un mentor con ese id")
        );

        return modelMapper.map(mentor, MentorResponseDto.class);
    }

    @Override
    public List<MentorResponseDto> getAllMentors() {
        var mentors = mentorRepository.findAll();
        return mentors.stream()
                .map(mentor -> modelMapper.map(mentor, MentorResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MentorResponseDto createUserByMentorId(Long mentorId, String email, String password) {
        var user = userQueryService.createUser(mentorId, email, password);
        return updateMentorByUserId(mentorId, user.getId());
    }

    @Override
    public MentorResponseDto updateMentorByUserId(Long mentorId, Long userId) {
        var mentor = mentorRepository.findById(mentorId).orElseThrow(() ->
                new ResourceNotFoundException("No existe un mentor con ese id")
        );

        mentor.setUser_id(userId);
        var updatedMentor = mentorRepository.save(mentor);
        return modelMapper.map(updatedMentor, MentorResponseDto.class);
    }
}
