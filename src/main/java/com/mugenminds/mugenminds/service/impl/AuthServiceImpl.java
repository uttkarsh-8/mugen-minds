package com.mugenminds.mugenminds.service.impl;

import com.mugenminds.mugenminds.model.Role;
import com.mugenminds.mugenminds.model.User;
import com.mugenminds.mugenminds.payload.LoginDto;
import com.mugenminds.mugenminds.payload.RegisterDto;
import com.mugenminds.mugenminds.repository.RoleRepository;
import com.mugenminds.mugenminds.repository.UserRepository;
import com.mugenminds.mugenminds.security.JwtTokenProvider;
import com.mugenminds.mugenminds.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleRepository = roleRepository;
    }

    @Override
    public String Login(LoginDto loginDto) {
        // Attempt to authenticate the user with the provided username (or email) and password.
        // UsernamePasswordAuthenticationToken is a specific type of Authentication implementation used for this purpose.
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        // If authentication is successful, set the Authentication object in the SecurityContextHolder.
        // This effectively signs the user in, associating the authentication with the current security context (e.g., the current session).
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        // Return a success message indicating the user has been logged in successfully.
        return token;
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
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role userRole = roleRepository.findByRole("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("User Role not set."));
        user.setRoles(userRole);

        userRepository.save(user);

        return "User registered successfully";
    }
}
