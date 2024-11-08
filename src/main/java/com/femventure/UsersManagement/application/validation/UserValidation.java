package com.femventure.UsersManagement.application.validation;

import com.femventure.Shared.exception.ValidationException;

public class UserValidation {
    public static void validateUserPassword(String password) {
        if (password.length() < 8 ) {
            throw new ValidationException("La contraseña debe tener al menos 8 caracteres");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new ValidationException("La contraseña debe contener al menos una letra mayúscula");
        }
        if (!password.matches(".*[a-z].*")) {
            throw new ValidationException("La contraseña debe contener al menos una letra minúscula");
        }
        if (!password.matches(".*\\d.*")) {
            throw new ValidationException("La contraseña debe contener al menos un número");
        }
    }

    public static void validateUserEmail(String email) {
        if (!email.matches("^(.+)@(.+)$")) {
            throw new ValidationException("El email debe ser válido");
        }
    }
}
