package com.mugenminds.mugenminds.service;

import com.mugenminds.mugenminds.model.User;
import com.mugenminds.mugenminds.payload.UserDto;

import java.util.Optional;

public interface UserService {
    UserDto userData(String usernameOrEmail);
}
