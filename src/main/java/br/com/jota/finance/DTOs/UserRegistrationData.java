package br.com.jota.finance.DTOs;

import br.com.jota.finance.entities.enums.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationData(
        @NotBlank
        String name,
        @NotBlank @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        Perfil role
) {
}
