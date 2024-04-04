package com.mugenminds.mugenminds.service.impl;

import com.mugenminds.mugenminds.model.User;
import com.mugenminds.mugenminds.payload.UserDto;
import com.mugenminds.mugenminds.repository.UserRepository;
import com.mugenminds.mugenminds.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto userData(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new NoSuchElementException("User not found with username or email: " + usernameOrEmail));

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getUsername());

        return userDto;
    }
}


