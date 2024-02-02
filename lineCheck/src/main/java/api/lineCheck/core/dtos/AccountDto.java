package api.lineCheck.core.dtos;

<<<<<<< HEAD
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
=======
import jakarta.validation.constraints.*;

public record AccountDto(
        @NotNull(message = "O nome não pode ser nulo")
        @NotBlank(message = "O nome não pode ser vazio")
        @NotEmpty
        @Size(min = 3, message = "O nome deve ter no mímo 3 letras")
        String name,
        @NotNull(message = "O email não pode ser nulo")
        @NotBlank(message = "O email não pode ser vazio")
        @Email(message = "Deve ser um email válido")
        @NotEmpty
        String email,
        @NotNull(message = "O telefone não pode ser nulo")
        @NotBlank(message = "O telefone não pode ser vazio")
        @NotEmpty
        @Size(min = 11)
        String phone,
        @NotNull(message = "A senha não pode ser nula")
        @NotBlank(message = "A senha não pode ser vazia")
        @NotEmpty
>>>>>>> ffd3cf6d41a74f37063b1055779e4fb29abc350b
        String password
) {}
