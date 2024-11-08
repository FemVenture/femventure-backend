package com.femventure.UsersManagement.shared;

import com.femventure.UsersManagement.domain.dto.User.response.UserResponseDto;
import com.femventure.UsersManagement.service.UserServiceImpl;
import org.springframework.stereotype.Service;

@Service
class UserQueryServiceImpl implements UserQueryService {
    private final UserServiceImpl userService;

    UserQueryServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserResponseDto createUser(Long mentorId, String email, String password) {
        return userService.createUser(mentorId, email, password);
    }
}
