package api.lineCheck.core.dtos;

public record AccountDto(
        String name,
        String email,
        String phone,
        String password
) {}
