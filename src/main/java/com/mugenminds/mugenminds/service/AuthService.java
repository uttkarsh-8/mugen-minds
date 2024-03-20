package com.mugenminds.mugenminds.service;

import com.mugenminds.mugenminds.payload.LoginDto;
import com.mugenminds.mugenminds.payload.RegisterDto;

public interface AuthService {
    String Login(LoginDto loginDto);
    String Register(RegisterDto registerDto);
}
