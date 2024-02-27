package api.lineCheck.core.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LogisticDto(
        @NotNull(message = "Logistica não pode ser nula")
        @NotEmpty(message = "Logistica não pode ser vazia")
        String logistic
) {}
