package api.lineCheck.core.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountDto(
        @NotNull(message = "O nome não pode ser nulo")
        @NotBlank(message = "O nome não pode ser vazio")
        String name,
        @NotNull(message = "O email não pode ser nulo")
        @NotBlank(message = "O email não pode ser vazio")
        String email,
        @NotNull(message = "O telefone não pode ser nulo")
        @NotBlank(message = "O telefone não pode ser vazio")
        String phone,
        @NotNull(message = "A senha não pode ser nula")
        @NotBlank(message = "A senha não pode ser vazia")
        String password
) {}
