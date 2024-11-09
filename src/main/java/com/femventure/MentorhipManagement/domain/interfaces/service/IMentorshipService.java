package com.femventure.MentorhipManagement.domain.interfaces.service;

import com.femventure.MentorhipManagement.domain.dto.Mentorship.request.MentorshipRequestDto;
import com.femventure.MentorhipManagement.domain.dto.Mentorship.response.MentorshipResponseDto;

public interface IMentorshipService {
    //POST
    public abstract MentorshipResponseDto createMentorship(MentorshipRequestDto mentorshipRequestDto);
    //GET
    public abstract MentorshipResponseDto getMentorshipByMentorId(Long mentorId);
    public abstract int getMentorshipCount();
    //PATCH
    public abstract MentorshipResponseDto updateMentorshipByMentorId(Long mentorId, MentorshipRequestDto mentorshipRequestDto);
}