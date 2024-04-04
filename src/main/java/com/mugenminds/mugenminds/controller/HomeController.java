package com.mugenminds.mugenminds.controller;

import com.mugenminds.mugenminds.payload.UserDto;
import com.mugenminds.mugenminds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ResponseEntity<UserDto> home(){

        String usernameOrEmail = getCurrentUsernameOrEmail();
        UserDto userDto = userService.userData(usernameOrEmail);

        return ResponseEntity.ok(userDto);
    }

    // RRetrieves the username or email which is currently signed in
    // it is necessary to get user specific data
    private String getCurrentUsernameOrEmail() {
        //principal == authenticated user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //checking if user is an instance of Userdetails Interface used by spring security
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername(); // For standard scenarios where the principal is a UserDetails object
        } else if (principal instanceof String) {
            return (String) principal; // For simpler authentication mechanisms
        }

        throw new IllegalStateException("User not found in security context");
    }
}
