package com.femventure.UsersManagement.shared;

import com.femventure.UsersManagement.domain.dto.User.response.UserResponseDto;

public interface UserQueryService {
    public abstract UserResponseDto createUser(String email, String password);
}
