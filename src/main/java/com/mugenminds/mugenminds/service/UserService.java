package com.mugenminds.mugenminds.service;

import com.mugenminds.mugenminds.payload.UserDto;

public interface UserService {
    UserDto userData(String usernameOrEmail);
}
