package api.lineCheck.core.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountDto(
        @NotBlank(message = "O nome não pode ser vazio")
        @NotNull(message = "O nome não pode ser nulo")
        String name,
        @NotBlank(message = "O email não pode ser vazio")
        @NotNull(message = "O email não pode ser nulo")
        String email,
        @NotBlank(message = "O telefone não pode ser vazio")
        @NotNull(message = "O telefone não pode ser nulo")
        String phone,
        @NotBlank(message = "A senha não pode ser vazio")
        @NotNull(message = "A senha não pode ser nulo")
        String password
) {}
