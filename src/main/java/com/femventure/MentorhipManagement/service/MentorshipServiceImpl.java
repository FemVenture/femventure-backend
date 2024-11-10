package com.femventure.MentorhipManagement.service;

import com.femventure.MentorhipManagement.application.validation.MentorshipValidation;
import com.femventure.MentorhipManagement.domain.dto.Mentorship.request.MentorshipRequestDto;
import com.femventure.MentorhipManagement.domain.dto.Mentorship.response.MentorshipResponseDto;
import com.femventure.MentorhipManagement.domain.entity.Mentorship;
import com.femventure.MentorhipManagement.domain.interfaces.repository.IMentorshipRepository;
import com.femventure.MentorhipManagement.domain.interfaces.service.IMentorshipService;
import com.femventure.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MentorshipServiceImpl implements IMentorshipService {
    private final IMentorshipRepository mentorshipRepository;
    private final ModelMapper modelMapper;

    public MentorshipServiceImpl(IMentorshipRepository mentorshipRepository, ModelMapper modelMapper) {
        this.mentorshipRepository = mentorshipRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MentorshipResponseDto createMentorship(MentorshipRequestDto mentorshipRequestDto) {
        MentorshipValidation.validateMentorship(mentorshipRequestDto);

        Mentorship mentorship = new Mentorship();
        mentorship.setMentorId(mentorshipRequestDto.getMentorId());
        mentorship.setAvailability(mentorshipRequestDto.getAvailability());
        mentorship.setIsMentorshipOffered(true);
        mentorship.setCost(mentorshipRequestDto.getCost());
        mentorship.setDescription(mentorshipRequestDto.getDescription());
        mentorship.setContactDetails(mentorshipRequestDto.getContactDetails());
        mentorship.setDuration(mentorshipRequestDto.getDuration());
        var createdMentorship = mentorshipRepository.save(mentorship);
        return modelMapper.map(createdMentorship, MentorshipResponseDto.class);
    }

    @Override
    public MentorshipResponseDto getMentorshipByMentorId(Long mentorId) {
        Mentorship mentorship = mentorshipRepository.findByMentorId(mentorId)
                .orElseThrow(() -> new ResourceNotFoundException("El mentor no brinda mentorías"));
        return modelMapper.map(mentorship, MentorshipResponseDto.class);
    }

    @Override
    public int getMentorshipCount() {
        return (int) mentorshipRepository.findAll().stream()
                .filter(Mentorship::getIsMentorshipOffered)
                .count();
    }

    @Override
    public MentorshipResponseDto updateMentorshipByMentorId(Long mentorId, MentorshipRequestDto mentorshipRequestDto) {
        Mentorship mentorship = mentorshipRepository.findByMentorId(mentorId)
                .orElseThrow(() -> new ResourceNotFoundException("El mentor no brinda mentorías"));

        if (!Objects.equals(mentorshipRequestDto.getAvailability(), mentorship.getAvailability()) &&
                mentorshipRequestDto.getAvailability() != null) {
            mentorship.setAvailability(mentorshipRequestDto.getAvailability());
        }
        if(!Objects.equals(mentorshipRequestDto.getCost(), mentorship.getCost())  &&
                mentorshipRequestDto.getCost() != null){
            if(mentorshipRequestDto.getCost()<0) throw new IllegalArgumentException("El costo no puede ser negativo");
            mentorship.setCost(mentorshipRequestDto.getCost());
        }
        if(!Objects.equals(mentorshipRequestDto.getDescription(), mentorship.getDescription()) && mentorshipRequestDto.getDescription()!=null) mentorship.setDescription(mentorshipRequestDto.getDescription());
        if(!Objects.equals(mentorshipRequestDto.getContactDetails(), mentorship.getContactDetails()) && mentorshipRequestDto.getContactDetails()!=null) mentorship.setContactDetails(mentorshipRequestDto.getContactDetails());
        if (mentorshipRequestDto.getDuration() != null &&
                !Objects.equals(mentorshipRequestDto.getDuration(), mentorship.getDuration())) {
            if (mentorshipRequestDto.getDuration() < 15 || mentorshipRequestDto.getDuration() > 120) {
                throw new IllegalArgumentException("La duración no puede ser menor a 15 min o mayor a 120 min");
            }
            mentorship.setDuration(mentorshipRequestDto.getDuration());
        }
        if(mentorshipRequestDto.getIsMentorshipOffered()!=mentorship.getIsMentorshipOffered()) mentorship.setIsMentorshipOffered(mentorshipRequestDto.getIsMentorshipOffered());

        var updatedMentorship = mentorshipRepository.save(mentorship);
        return modelMapper.map(updatedMentorship, MentorshipResponseDto.class);
    }
}
