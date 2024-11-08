package com.femventure.UsersManagement.service;

import com.femventure.Shared.exception.ResourceNotFoundException;
import com.femventure.UsersManagement.domain.dto.User.response.UserResponseDto;
import com.femventure.UsersManagement.domain.interfaces.repository.IUserRepository;
import com.femventure.UsersManagement.domain.interfaces.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(IUserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDto getUserForLogin(String email, String password) {
        var user = userRepository.findByEmailAndPassword(email, password).orElseThrow(() ->
                new ResourceNotFoundException("No existe un usuario con ese email y password")
        );

        return modelMapper.map(user, UserResponseDto.class);
    }
}

