package com.femventure.UsersManagement.domain.interfaces.service;

import com.femventure.UsersManagement.domain.dto.Mentor.request.MentorRequestDto;
import com.femventure.UsersManagement.domain.dto.Mentor.response.MentorResponseDto;

import java.util.List;

public interface IMentorService {
    //POST
    public abstract MentorResponseDto createMentor(MentorRequestDto mentor);
    public abstract MentorResponseDto createUserByMentorId(Long id, String email, String password);

    //GET
    public abstract MentorResponseDto getMentorById(Long id);
    public abstract List<MentorResponseDto> getAllMentors();
    //PUT
    public abstract MentorResponseDto updateMentorByUserId(Long mentorId, Long userId);
}
