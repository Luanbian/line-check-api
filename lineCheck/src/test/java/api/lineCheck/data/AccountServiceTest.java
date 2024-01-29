package api.lineCheck.data;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.data.usecase.AccountService;
import api.lineCheck.domain.Account;
import api.lineCheck.infra.repositories.JPAAccount;
import api.lineCheck.mocks.AccountDtoMock;
import api.lineCheck.presentation.exceptions.EmailAlreadyExistsException;
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
}
