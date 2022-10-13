package com.Howard.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.Howard.model.Role;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserDTO {
	private Long id;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private @NonNull String email;
    @NotEmpty(message = "Password should be empty")
    private @NonNull String password;
    private @NonNull Set<Role> roles;
}
