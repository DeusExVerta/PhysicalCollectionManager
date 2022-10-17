package com.Howard.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.Howard.entity.Role;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserDTO {
	
    @NotEmpty(message = "Email should not be empty")
    @Email
    private @NonNull String email;
    @NotEmpty(message = "Password should be empty")
    private @NonNull String password;
    private Set<Role> roles;
}
