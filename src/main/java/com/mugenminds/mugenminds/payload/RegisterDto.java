package com.mugenminds.mugenminds.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotNull(message = "'username cannot be left empty'")
    private String username;
    @NotNull(message = "'password' cannot be empty")
    private String password;
    @Email(message = "'email' field has to be valid")
    private String email;
}
