package com.femventure.UsersManagement.application.validation;

import com.femventure.Shared.exception.ValidationException;
import com.femventure.UsersManagement.domain.dto.Mentor.request.MentorRequestDto;

public class MentorValidation {
    public static void ValidateMentor(MentorRequestDto mentorRequestDto) {
        if(mentorRequestDto.getFullName().isEmpty()){
            throw new ValidationException("El nombre completo es obligatorio");
        }
        if(mentorRequestDto.getLocation().isEmpty()){
            throw new ValidationException("La ubicación es obligatoria");
        }
        if(mentorRequestDto.getDescription().isEmpty()){
            throw new ValidationException("La descripción es obligatoria");
        }
        if(mentorRequestDto.getImage_url().isEmpty()){
            throw new ValidationException("La imagen es obligatoria");
        }
    }
}
