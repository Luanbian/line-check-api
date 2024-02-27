package api.lineCheck.core.dtos;

import jakarta.validation.constraints.*;

public record DriverServiceDto (
        @NotNull(message = "O serviço não pode ser nulo")
        @NotEmpty(message = "O serviço não pode ser vazio")
        String service
) { }
