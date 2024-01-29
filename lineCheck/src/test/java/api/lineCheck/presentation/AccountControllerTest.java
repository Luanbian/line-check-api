package api.lineCheck.presentation;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.data.interfaces.IAccountService;
import api.lineCheck.mocks.AccountDtoMock;
import api.lineCheck.presentation.controllers.AccountController;
import api.lineCheck.presentation.exceptions.EmailAlreadyExistsException;
import api.lineCheck.presentation.exceptions.PhoneAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
    @InjectMocks
    public AccountController sut;
    @Mock
    public IAccountService service;
    public AccountDtoMock dtoMock = new AccountDtoMock();
    @Test
    public void should_return_200_if_success() {
        AccountDto dto = dtoMock.main();
        ResponseEntity response = sut.create(dto);
        verify(service, times(1)).register(dto);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }
    @Test
    public void should_fall_in_catch_if_email_already_exists() {
        String existingEmail = "existing@email.com";
        AccountDto dto = dtoMock.main();
        dto = new AccountDto(dto.name(), existingEmail, dto.phone(), dto.password());
        when(service.register(dto)).thenThrow(new EmailAlreadyExistsException());
        ResponseEntity response = sut.create(dto);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("email já cadastrado no sistema", response.getBody());
    }
    @Test
    public void should_fall_in_catch_if_phone_already_exists() {
        String existingPhone = "existing@email.com";
        AccountDto dto = dtoMock.main();
        dto = new AccountDto(dto.name(), dto.email(), existingPhone, dto.password());
        when(service.register(dto)).thenThrow(new PhoneAlreadyExistsException());
        ResponseEntity response = sut.create(dto);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Telefone já cadastrado no sistema", response.getBody());
    }
}
