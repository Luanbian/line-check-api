package api.lineCheck.core.dtos;

import jakarta.validation.constraints.NotNull;

public record KmDto(
    @NotNull(message = "O quilômetro inicial não pode ser nulo")
    Integer initialKm,
    @NotNull(message = "O quilômetro final não pode ser nulo")
    Integer finalKm
) { }
