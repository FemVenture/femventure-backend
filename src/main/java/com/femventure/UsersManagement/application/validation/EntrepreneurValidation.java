package com.femventure.UsersManagement.application.validation;

import com.femventure.Shared.exception.ValidationException;
import com.femventure.UsersManagement.domain.dto.Entrepreneur.request.EntrepreneurRequestDto;

public class EntrepreneurValidation {
    public static void ValidateEntrepreneur(EntrepreneurRequestDto entrepreneurRequestDto) {
        if(entrepreneurRequestDto.getLocation().isEmpty()){
            throw new ValidationException("La ubicación es obligatoria");
        }
        if(entrepreneurRequestDto.getDescription().isEmpty()){
            throw new ValidationException("La descripción es obligatoria");
        }
        if(entrepreneurRequestDto.getImage_url().isEmpty()){
            throw new ValidationException("La imagen es obligatoria");
        }
        if(entrepreneurRequestDto.getFullName().isEmpty()){
            throw new ValidationException("El nombre completo es obligatorio");
        }
    }
}
