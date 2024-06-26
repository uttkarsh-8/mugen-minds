package com.mugenminds.mugenminds.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "In 'usernameOrEmail' Please enter a username or an email")
    private String usernameOrEmail;
    @NotBlank(message = "In 'password' Please enter a password")
    private String password;
}
