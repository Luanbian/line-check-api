package api.lineCheck.data;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.data.usecase.AccountService;
import api.lineCheck.domain.Account;
import api.lineCheck.domain.Role;
import api.lineCheck.infra.repositories.JPAAccount;
import api.lineCheck.mocks.AccountDtoMock;
import api.lineCheck.presentation.exceptions.EmailAlreadyExistsException;
import api.lineCheck.presentation.exceptions.InvalidRoleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @InjectMocks
    public AccountService sut;
    @Mock
    public JPAAccount repository;
    public AccountDtoMock dtoMock = new AccountDtoMock();
    @Test
    public void should_be_able_to_register_account_with_correct_params() {
        AccountDto dto = dtoMock.main();
        Account result = sut.register(dto);
        assertNotNull(result);
        assertEquals(dto.name(), result.getName());
        assertEquals(dto.email(), result.getEmail());
        assertEquals(dto.phone(), result.getPhone());
        assertEquals(dto.password(), result.getPassword());
        assertEquals(dto.role(), result.getRole().toString());
    }
    @Test
    public void should_convert_string_manager_to_role_manager() {
        String role = "manager";
        AccountDto dto = dtoMock.main();
        dto = new AccountDto(dto.name(), dto.email(), dto.phone(), dto.password(), role);
        Account result = sut.register(dto);
        assertEquals(result.getRole(), Role.MANAGER);
    }
    @Test
    public void should_convert_string_driver_to_role_driver() {
        String role = "driver";
        AccountDto dto = dtoMock.main();
        dto = new AccountDto(dto.name(), dto.email(), dto.phone(), dto.password(), role);
        Account result = sut.register(dto);
        assertEquals(result.getRole(), Role.DRIVER);
    }
    @Test
    public void should_call_repository_with_correct_values() {
        Account result = sut.register(dtoMock.main());
        verify(repository, times(1)).create(result);
    }
    @Test
    public void should_throw_if_repository_throw_an_Exception() {
        Account result = sut.register(dtoMock.main());
        doAnswer(invocation -> {
            throw new Exception();
        }).when(repository).create(result);
        verify(repository, times(1)).create(result);
        assertThrows(Exception.class, () -> sut.register(dtoMock.main()));
    }
    @Test
    public void should_throw_InvalidRoleException_if_role_was_invalid() {
        String invalidRole = "invalid_role";
        AccountDto dto = dtoMock.main();
        dto = new AccountDto(dto.name(), dto.email(), dto.phone(), dto.password(), invalidRole);
        AccountDto finalDto = dto;
        assertThrows(InvalidRoleException.class, () -> sut.register(finalDto));
    }
}
