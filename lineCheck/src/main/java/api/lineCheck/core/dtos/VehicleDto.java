package api.lineCheck.core.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record VehicleDto(
        @NotEmpty(message = "O veiculo não pode ser vazio")
        @NotNull(message = "O veiculo não pode ser nulo")
        String vehicle
) { }
