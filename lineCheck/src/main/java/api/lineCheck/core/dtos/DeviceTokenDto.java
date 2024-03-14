package api.lineCheck.core.dtos;

import jakarta.validation.constraints.*;

public record DeviceTokenDto(
        @NotNull(message = "O id da conta não pode ser nulo")
        @NotEmpty(message = "O id da conta não pode ser vazio")
        String accountId,
        @NotNull(message = "O token do celular não pode ser nulo")
        @NotEmpty(message = "O token da celular não pode ser vazio")
        String deviceToken
) { }
