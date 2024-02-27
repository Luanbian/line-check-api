package api.lineCheck.core.dtos;

import jakarta.validation.constraints.*;

public record ManufactureDto(
        @NotNull(message = "fábrica não pode ser nula")
        @NotEmpty(message = "fábrica não pode ser vazio")
        String manufacture
) { }
