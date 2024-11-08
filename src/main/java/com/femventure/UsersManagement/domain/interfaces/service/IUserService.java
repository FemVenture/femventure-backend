package com.femventure.UsersManagement.domain.interfaces.service;

import com.femventure.UsersManagement.domain.dto.User.response.UserResponseDto;

public interface IUserService {
    //GET
    public abstract UserResponseDto getUserForLogin(String email, String password);
}
