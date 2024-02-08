package api.lineCheck.domain.account;

public record AccountProps(
         String name,
         String email,
         String phone,
         String password,
         Role role
) {}
