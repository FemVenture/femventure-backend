package com.femventure.MentorhipManagement.application.validation;

import com.femventure.MentorhipManagement.domain.dto.Mentorship.request.MentorshipRequestDto;
import com.femventure.Shared.exception.ValidationException;

public class MentorshipValidation {
    public static void validateMentorship (MentorshipRequestDto mentorshipRequestDto) {
        if (mentorshipRequestDto.getCost() == null || mentorshipRequestDto.getCost() < 0) {
            throw new ValidationException("El costo no puede ser un valor negativo");
        }
        if (mentorshipRequestDto.getDuration() < 15 || mentorshipRequestDto.getDuration() > 120) {
            throw new ValidationException("La duración no puede ser menor a 15 minutos o mayor a 120 minutos");
        }
        if (mentorshipRequestDto.getDescription().isEmpty()) {
            throw new ValidationException("La descripción no puede ser nula");
        }
        if (mentorshipRequestDto.getContactDetails().isEmpty()) {
            throw new ValidationException("Los detalles de contacto no pueden ser nulos");
        }
        if (mentorshipRequestDto.getAvailability().isEmpty()) {
            throw new ValidationException("La disponibilidad horaria no puede ser nula");
        }
    }
}
