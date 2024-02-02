package api.lineCheck.presentation.controllers;

import api.lineCheck.core.dtos.AuthParams;
import api.lineCheck.data.interfaces.IAuthService;
import api.lineCheck.domain.Account;
import api.lineCheck.presentation.exceptions.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final IAuthService service;
    @Autowired
    public AuthController(IAuthService service) {
        this.service = service;
    }
    @PostMapping("/login")
    public ResponseEntity login(@Validated @RequestBody AuthParams authParams) {
        try {
            String token = service.login(authParams.email(), authParams.password());
            return ResponseEntity.ok(token);
        } catch (InvalidCredentialsException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}
