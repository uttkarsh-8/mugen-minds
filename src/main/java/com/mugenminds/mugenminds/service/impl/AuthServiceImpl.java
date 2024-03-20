package com.mugenminds.mugenminds.service.impl;

import com.mugenminds.mugenminds.model.Role;
import com.mugenminds.mugenminds.model.User;
import com.mugenminds.mugenminds.payload.LoginDto;
import com.mugenminds.mugenminds.payload.RegisterDto;
import com.mugenminds.mugenminds.repository.RoleRepository;
import com.mugenminds.mugenminds.repository.UserRepository;
import com.mugenminds.mugenminds.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String Login(LoginDto loginDto) {
        return null;
    }

    @Override
    public String Register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new BadCredentialsException("Email already exists");
        }

        if (userRepository.existsByUsername(registerDto.getUsername())){
            throw new BadCredentialsException("Username already exists");
        }

        User user = new User();

        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());

        user.setRoles(new Role(2,"ROLE_USER"));

        userRepository.save(user);

        return "User registered successfully";
    }
}
