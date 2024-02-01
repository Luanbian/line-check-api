package api.lineCheck.presentation;

import api.lineCheck.core.dtos.AuthParams;
import api.lineCheck.data.interfaces.IAuthService;
import api.lineCheck.mocks.AccountPropsMock;
import api.lineCheck.presentation.controllers.AuthController;
import static org.junit.jupiter.api.Assertions.*;

import api.lineCheck.presentation.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @InjectMocks
    public AuthController sut;
    @Mock
    public IAuthService service;
    public AccountPropsMock propsMock = new AccountPropsMock();
    @Test
    public void should_return_token_if_credentials_are_valid() {
        String token = "fake_token";
        String email = propsMock.email;
        String password = propsMock.password;
        when(service.login(email, password)).thenReturn(token);
        AuthParams validParams = new AuthParams(email, password);
        ResponseEntity response = sut.login(validParams);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody());
    }
    @Test
    public void should_return_bad_request_if_credentials_are_invalid() {
        String email = "invalid@email.com";
        String password = "invalid_password";
        when(service.login(email, password)).thenThrow(new InvalidCredentialsException());
        AuthParams invalidParams = new AuthParams(email, password);
        ResponseEntity response = sut.login(invalidParams);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Suas credenciais estão inválidas", response.getBody());
    }
    @Test
    public void should_return_internal_server_error_if_throw_unexpected_error() {
        String email = propsMock.email;
        String password = propsMock.password;
        doAnswer(invocation -> {
            throw new Exception();
        }).when(service).login(email, password);
        AuthParams validParams = new AuthParams(email, password);
        ResponseEntity response = sut.login(validParams);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno do servidor", response.getBody());
    }
}
