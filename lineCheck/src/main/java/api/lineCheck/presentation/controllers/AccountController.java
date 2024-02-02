package api.lineCheck.presentation.controllers;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.data.interfaces.IAccountService;
import api.lineCheck.domain.Account;
import api.lineCheck.presentation.exceptions.EmailAlreadyExistsException;
import api.lineCheck.presentation.exceptions.PhoneAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {
    private final IAccountService service;
    @Autowired
    public AccountController(IAccountService service) {
        this.service = service;
    }
    @PostMapping("/account")
<<<<<<< HEAD
    public ResponseEntity create(@RequestBody @Valid AccountDto dto) {
=======
    public ResponseEntity create(@Validated @RequestBody AccountDto dto) {
>>>>>>> ffd3cf6d41a74f37063b1055779e4fb29abc350b
        try {
            Account account = service.register(dto);
            return ResponseEntity.ok(account);
        } catch (EmailAlreadyExistsException | PhoneAlreadyExistsException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}
