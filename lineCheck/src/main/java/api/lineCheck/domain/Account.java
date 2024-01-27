package api.lineCheck.domain;

import lombok.Data;

import java.util.UUID;
@Data
public class Account {
    private UUID id;
    private String email;
    private String phone;
    private String password;
}