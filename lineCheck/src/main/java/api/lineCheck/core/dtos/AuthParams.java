package api.lineCheck.core.dtos;

import jakarta.validation.constraints.*;

public record AuthParams(
        @NotNull(message = "O email não pode ser nulo")
        @NotBlank(message = "O email não pode ser vazio")
        @Email(message = "Deve ser um email válido")
        @NotEmpty
        String email,
        @NotNull(message = "A senha não pode ser nula")
        @NotBlank(message = "A senha não pode ser vazia")
        @NotEmpty
        String password
) { }
